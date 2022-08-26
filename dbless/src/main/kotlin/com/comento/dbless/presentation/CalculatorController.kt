package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.CalculatorRequestDto
import com.comento.dbless.service.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    /**
     * Integer 숫자와 수식 ( +, - , *, / , ^ )로 이루어진 수식 표현과 round Integer를 주면, 해당 수식을 실행한 이후, 해당 소숫점자리 까지 rounding을 하여 client에게 응답을 주는 API
     */
    @PostMapping("/calculate")
    fun calculate(@RequestBody requestDto: CalculatorRequestDto): Number {
        val str = requestDto.expression.split(" ")
        return calculatorService.calculating(str,requestDto.round)
    }

}