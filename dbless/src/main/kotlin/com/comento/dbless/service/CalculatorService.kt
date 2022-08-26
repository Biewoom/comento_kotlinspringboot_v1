package com.comento.dbless.service

import org.springframework.stereotype.Service
import kotlin.math.pow
import kotlin.random.Random

@Service
class CalculatorService {
    /**
     * String util : .있으면 double 없으면 int
     */
    private fun String.toNumber(): Number {
        return when (this.contains(".")) {
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
     * 소수점 반올림 함수
     */
    private fun Double.round(digits: Int): Double {
        return Math.round(this * Math.pow(10.0, digits.toDouble())) / Math.pow(10.0, digits.toDouble())
    }

    /**
     * 사용자 지정 범위 내의 랜덤 숫자 반환 함수
     */
    fun makeRandomNumber(first: String, last: String): Number {
        val firstN = first.toNumber()
        val lastN = last.toNumber()

        return when {
            firstN is Int && lastN is Int -> Random.nextInt(firstN, lastN)
            else -> {
                Random.nextDouble(firstN.toDouble(), lastN.toDouble())
                    .round(Math.max(first.getDecimalPlace(), last.getDecimalPlace()))
            }
        }
    }

    /**
     * 특정 값 없음 -1 리턴
     */
    private fun List<String>.findIndex(item: String): Int {
        for (i in this.indices) {
            if (this[i] == item) return i
        }
        return -1
    }

    /**
     *  * or / 와 같이 두개 비교
     */
    private fun List<String>.findIndex(item: String, item2: String): Int {
        for (i in this.indices) {
            if (this[i] == item || this[i] == item2) return i
        }
        return -1
    }

    /**
     * 연산 하드코딩
     */
    fun calculating(value: List<String>, decimalPlace: Int) : Number {
        val mutableValues: MutableList<String> = value.toMutableList()

        while (mutableValues.findIndex("^") != -1) {
            val index = mutableValues.findIndex("^")
            mutableValues[index - 1] =
                mutableValues[index - 1].toDouble().pow(mutableValues[index + 1].toDouble()).toString()
            mutableValues.removeAt(index + 1)
            mutableValues.removeAt(index)
        }

        while (mutableValues.findIndex("*", "/") != -1) {
            val index = mutableValues.findIndex("*", "/")
            if (mutableValues[index] == "*") mutableValues[index - 1] =
                (mutableValues[index - 1].toDouble() * mutableValues[index + 1].toDouble()).toString()
            else mutableValues[index - 1] =
                (mutableValues[index - 1].toDouble() / mutableValues[index + 1].toDouble()).toString()
            mutableValues.removeAt(index + 1)
            mutableValues.removeAt(index)
        }

        while (mutableValues.findIndex("+","-") != -1) {
            val index = mutableValues.findIndex("+", "-")
            if (mutableValues[index] == "+") mutableValues[index - 1] =
                (mutableValues[index - 1].toDouble() + mutableValues[index + 1].toDouble()).toString()
            else mutableValues[index - 1] =
                (mutableValues[index - 1].toDouble() - mutableValues[index + 1].toDouble()).toString()
            mutableValues.removeAt(index + 1)
            mutableValues.removeAt(index)
        }

        return mutableValues.get(0).toDouble().round(decimalPlace)
    }


}