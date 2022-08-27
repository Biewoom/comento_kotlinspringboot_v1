package com.comento.dbless.service

import com.comento.dbless.getDecimalPoint
import com.comento.dbless.toNumber
import com.comento.dbless.toRound
import org.springframework.stereotype.Service
import java.lang.Integer.max
import kotlin.random.Random

@Service
class CalculatorService {

    fun generateRandomNumber(start: String, end: String): Number {
        val startNum = start.toNumber()
        val endNum = end.toNumber()

        val startNumDecimalPoint = start.getDecimalPoint()
        val endNumDecimalPoint = end.getDecimalPoint()

        return when {
            startNum is Long && endNum is Long -> Random.nextLong(startNum, endNum)
            else -> Random.nextDouble(startNum.toDouble(), endNum.toDouble())
                .toRound(max(startNumDecimalPoint, endNumDecimalPoint))
        }
    }
}