package com.comento.dbless.service

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class CalculatorService {
    /**
     * String util : .있으면 double 없으면 int
     */
    private fun String.toNumber(): Number {
        return when(this.contains(".")) {
            true -> this.toDouble()
            false -> this.toInt()
        }
    }

    /**
     * String util : 소수점 자리수 리턴
     */
    private fun String.getDecimalPlace(): Int {
        val str_arr = this.split(".")

        if (str_arr.size == 2) {
            return str_arr.get(1).length
        } else {
            return 0
        }
    }

    /**
     * 소수점 아래 내림 함수
     */
    private fun Double.round(digits : Int):Double {
        return Math.floor(this * Math.pow(10.0, digits.toDouble())) / Math.pow(10.0, digits.toDouble())
    }

    /**
     * 사용자 지정 범위 내의 랜덤 숫자 반환 함수
     */
    fun makeRandomNumber(first: String, last: String) : Number {
        val firstN = first.toNumber()
        val lastN = last.toNumber()

        return when {
            firstN is Int && lastN is Int -> Random.nextInt(firstN,lastN)
            else -> {
                Random.nextDouble(firstN.toDouble(),lastN.toDouble()).round(Math.max(first.getDecimalPlace(),last.getDecimalPlace()))
            }
        }
    }
}