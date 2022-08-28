package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.CalculatorRequest
import com.comento.dbless.service.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(
    private val calculatorService: CalculatorService
) {
    @GetMapping("/generate/{range}")
    fun generateRandomNumber(@PathVariable("range") range: String): Number {
        val (start, end) = range.filter { !it.isWhitespace() }.split("~")
        return calculatorService.generateRandomNumber(start, end)
    }

    @PostMapping("/calculate")
    fun calculateNumber(@RequestBody request: CalculatorRequest): Double {
        val (expression, round) = request
        return calculatorService.calculate(expression, round)
    }

}