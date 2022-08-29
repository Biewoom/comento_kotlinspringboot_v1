package com.comento.dbless.presentation

import com.comento.dbless.service.CalculatorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(val calculatorService: CalculatorService) {

    // Rest Controller + Mapping Annotation 으로 API 구성

    /**
     * 하나의 메시지를 출력
     */
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
    fun generateRandomNumber(@PathVariable("range") range: String): Number {
        return calculatorService.generateRandomNumber(range)
    }
}