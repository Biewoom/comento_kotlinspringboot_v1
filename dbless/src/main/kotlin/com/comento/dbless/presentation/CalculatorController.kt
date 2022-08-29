package com.comento.dbless.presentation

import com.comento.dbless.SRequestBody
import com.comento.dbless.logger
import com.comento.dbless.presentation.dto.Calculator
import com.comento.dbless.service.CalculatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(
    private val calculatorService: CalculatorService
) {

    @Operation(summary = "generate RandomNumber", description = "해당 범위 안에 있는 Numbers 중 랜덤으로 생성")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Number::class, example = "1.0"))]),
        ApiResponse(responseCode = "400", description = "Invalid range String", content = [Content(schema = Schema(implementation = String::class, example = "cause: **"))]),
        ApiResponse(responseCode = "500", description = "UnKnown Error", content = [Content()])
    ])
    @Parameter(name = "range", description = "~를 사이를 두고 두 개의 Numbers", example = "1~5")
    @GetMapping("/generate/{range}")
    fun generateRandomNumber( @PathVariable("range") range: String): ResponseEntity<*> {
        return try {
            MDC.clear()
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.info { "range: $range"}
            ResponseEntity.status(HttpStatus.OK).body(calculatorService.getRandomNum(range))
        } catch(e: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
        } catch(e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error] If Under Emergency, Please contact by emil likemin014@gmail.com")
        }

    }

    @Operation(summary = "Expression을 보내면, 해당 요구대로 Number 값 계산", description = "Expression을 보내면, 해당 요구대로 Number 값 계산", requestBody = SRequestBody(
        description = "Calculator 요구", required =  true
    ))
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Int::class, example = "1.0"))]),
        ApiResponse(responseCode = "400", description = "Invalid Expression", content = [Content(schema = Schema(implementation = String::class, example = "cause: **"))]),
        ApiResponse(responseCode = "500", description = "UnKnown Error", content = [Content()])
    ])
    @PostMapping("/calculate")
    fun calculate(@RequestBody dto: Calculator): ResponseEntity<*> = runCatching {
        val (expr, roundNum) = dto
        calculatorService.calculate(expr, roundNum)
    }.mapCatching { result ->
        ResponseEntity.ok(result)
    }.recoverCatching { e ->
        when (e){
            is IllegalArgumentException -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
            else -> throw e
        }
    }.getOrNull() ?: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error] If Under Emergency, Please contact by emil likemin014@gmail.com")

}