package com.comento.dbless.service

import com.comento.dbless.toNumber
import com.comento.dbless.toRound
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Math.max
import kotlin.random.Random

private fun String.getDecimalPoint() = this.split(".").getOrNull(1)?.length ?: 0

@Service
class CalculatorService {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun generateRandomNumber(range: String): Number {

        // [ 예외처리 ] '~' 구분자가 없이 입력된 경우
        if (!range.contains("~")) throw IllegalArgumentException("request should contain `~`")

        // filter를 통해 '~'를 기준으로 분리
        val numbers = range.filter { !it.isWhitespace() }.split("~")

        // [ 예외처리 ] 숫자가 부족한 경우
        if (numbers.size != 2) throw IllegalArgumentException("request should contain `2 number values`")

        // 시작, 끝 수
        val (start, end) = numbers
        logger.info("start: $start , end: $end")

        // 시작, 끝 수의 자료형을 변환
        val startNum = start.toNumber()
        val endNum = end.toNumber()

        // 소수 처리
        val startNumDecimalPoint = start.getDecimalPoint()
        val endNumDecimalPoint = end.getDecimalPoint()
        logger.info("startNum: $startNum , end: $endNum")
        logger.info("startNumCounting: $startNumDecimalPoint , endNumCounting: $endNumDecimalPoint")

        // 반환
        return when {
            // 시작, 끝 수가 '정수'
            startNum is Long && endNum is Long -> Random.nextLong(startNum, endNum)
            // 시작, 끝 수가 '소수'
            else -> Random.nextDouble(startNum.toDouble(), endNum.toDouble()).toRound(max(startNumDecimalPoint, endNumDecimalPoint))
        }
    }
}