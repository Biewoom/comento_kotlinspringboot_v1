package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.Calculator
import com.comento.dbless.presentation.dto.Message
import com.comento.dbless.service.CalculatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.util.*

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(val calculatorService: CalculatorService) {

    private val logger = KotlinLogging.logger(javaClass.name) // Logger

    @Operation(summary = "Generate Random Number", description = "해당 범위 안에 있는 Numbers 중 랜덤으로 생성")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = Number::class, example = "1.0")
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid Range String",
                content = [Content(schema = Schema(implementation = String::class, example = "cause: **"))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Unknown Error",
                content = [Content()]
            )
        ]
    )
    @Parameter(name = "range", description = "~를 사이를 두고 두 개의 Numbers", example = "1~5")
    @GetMapping("/generate/{range}")
    fun generateRandomNumber(@PathVariable("range") range: String): ResponseEntity<*> {
        return try {
            MDC.clear()
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.info { "range : $range " }
            ResponseEntity.status(HttpStatus.OK).body(calculatorService.generateRandomNumber(range))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("[Unknown Error]")
        }
    }

    @Operation(summary = "Calculate number")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Success",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]
            ),
            ApiResponse(responseCode = "400", description = "IllegalArgumentException", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Unknown Exception", content = [Content()])
        ]
    )
    @PostMapping("/calculate")
    fun calculate(@RequestBody dto: Calculator): ResponseEntity<*> = runCatching {
        val (expr, roundNum) = dto
        logger.info { "expr : $expr, roundNum : $roundNum" }
        calculatorService.calculate(expr, roundNum)
    }.mapCatching { result ->
        ResponseEntity.ok(result)
    }.recoverCatching { e ->
        when (e) {
            is IllegalArgumentException -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
            else -> throw e
        }
    }.getOrNull() ?: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error]")
}