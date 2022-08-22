package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.Calculator
import com.comento.dbless.service.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController(
    private val calculatorService: CalculatorService
) {

    @GetMapping("/generate/{range}")
    fun generateRandomNumber(@PathVariable("range") range: String, httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): Number {
        val (start, end) = range.filter { !it.isWhitespace() }.split("~")
        return calculatorService.getRandomNum(start, end)
    }

    @PostMapping("/calculate")
    fun calculate(@RequestBody dto: Calculator): Double {
        val (expr, roundNum) = dto
        return calculatorService.calculate(expr, roundNum)
    }
}