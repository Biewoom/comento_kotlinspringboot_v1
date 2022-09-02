package com.comento.dbless.presentation

import com.comento.dbless.logger
import com.comento.dbless.presentation.dto.CalculatorRequest
import com.comento.dbless.service.CalculatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.apache.logging.log4j.message.Message
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(
    private val calculatorService: CalculatorService
) {
    @Operation(summary = "Generate random number from range") // 1. Operation
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]), // 2. ApiResponse
        ApiResponse(responseCode = "400", description = "IllegalArgumentException", content = [Content()]),
        ApiResponse(responseCode = "500", description = "Unknown Exception", content = [Content()])
    ])
    @GetMapping("/generate/{range}")
    fun generateRandomNumber(@PathVariable("range") range: String): ResponseEntity<*> {
        val (start, end) = range.filter { !it.isWhitespace() }.split("~")
        MDC.clear()
        MDC.put("requestId", UUID.randomUUID().toString())
        logger.info { "range: $range"}
        return try {
            MDC.clear()
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.info { "range: $range"}
            ResponseEntity.status(HttpStatus.OK).body(calculatorService.generateRandomNumber(start, end))
        } catch(e: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
        } catch(e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error] If Under Emergency, Please contact by emil likemin014@gmail.com")
        }
    }
    @Operation(summary = "Calculate number") // 1. Operation
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]), // 2. ApiResponse
        ApiResponse(responseCode = "400", description = "IllegalArgumentException", content = [Content()]),
        ApiResponse(responseCode = "500", description = "Unknown Exception", content = [Content()])
    ])
    @PostMapping("/calculate")
    fun calculateNumber(@RequestBody request: CalculatorRequest): Double {
        val (expression, round) = request
        MDC.clear()
        MDC.put("requestId", UUID.randomUUID().toString())
        logger.info { "expression: $expression, round: $round"}
        return calculatorService.calculate(expression, round)
    }

}