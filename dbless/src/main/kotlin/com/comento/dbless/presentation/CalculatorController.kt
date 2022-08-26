package com.comento.dbless.presentation

import com.comento.dbless.service.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorController (val calculatorService: CalculatorService){

    /**
     * 숫자 범위의 String을 URL path로 넘겨서, 해당 범위 안의 랜덤한 값을 client에게 돌려주는 API
     */
    @GetMapping("/generate/{range}")
    fun generateNumber(@PathVariable("range") range: String): Number {
        val str = range.split("~")
        if (str.size != 2) throw IllegalArgumentException()
        return calculatorService.makeRandomNumber(str.get(0),str.get(1))
    }
}