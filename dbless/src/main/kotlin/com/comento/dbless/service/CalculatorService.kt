package com.comento.dbless.service

import com.comento.dbless.domain.Divide
import com.comento.dbless.domain.Expr
import com.comento.dbless.domain.Minus
import com.comento.dbless.domain.Num
import com.comento.dbless.domain.Pow
import com.comento.dbless.domain.Sum
import com.comento.dbless.domain.Time
import com.comento.dbless.logger
import com.comento.dbless.toNumber
import com.comento.dbless.toRound
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Math.max
import kotlin.random.Random

private fun String.getDecimalPoint() = this.split(".").getOrNull(1)?.length ?: 0
private inline fun <T> List<T>.firstIndexOrNull(predicate: (T) -> Boolean): Int? = run {
    val res = this.indexOfFirst(predicate)
    if (res == -1) return null
    else return res
}

@Service
class CalculatorService {
    fun getRandomNum(range: String): Number {

        if (!range.contains("~")) throw IllegalArgumentException("request should contain `~`")

        val numbers = range.filter { !it.isWhitespace() }.split("~")

        if ( numbers.size != 2 ) throw IllegalArgumentException("request should contain `2 number values`")

        val (start, end) = numbers
        logger.info("start: $start , end: $end")

        val startNum = start.toNumber()
        val endNum = end.toNumber()

        val startNumDecimalPoint = start.getDecimalPoint()
        val endNumDecimalPoint = end.getDecimalPoint()

        logger.info("startNum: $startNum , end: $endNum")
        logger.info("startNumCounting: $startNumDecimalPoint , endNumCounting: $endNumDecimalPoint")

        return when {
            startNum is Long && endNum is Long -> Random.nextLong(startNum, endNum)
            else -> Random.nextDouble(startNum.toDouble(), endNum.toDouble())
                .toRound(max(startNumDecimalPoint, endNumDecimalPoint))
        }
    }

    fun calculate(expr: String, roundNum: Int): Double {
        val ll = makeStringList(expr)
        val expr = parseExpression(ll)
        return expr.evalFun().toRound(roundNum)
    }

    private fun makeStringList(str: String): List<String> {
        if (str.isEmpty()) return emptyList()
        val res = mutableListOf<String>()

        val strWithoutSpace = str.filter { !it.isWhitespace() }

        var num = ""
        strWithoutSpace.forEach {
            if ( it in listOf('+', '-', '*', '/', '^') ) {
                res.add(num)
                res.add(it.toString())
                num = ""
            }
            else{
                if ( !it.isDigit() ) throw IllegalArgumentException("`$it` should be digit ")
                num += it
            }
        }
        res.add(num)
        return res.toList()
    }

    private fun parseExpression(ll: List<String>): Expr {
        if (ll.size == 1) return Num(ll[0].toDouble())

        ll.firstIndexOrNull { it == "+" || it == "-" }?.let{
            when (ll[it]) {
                "+" -> return Sum(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
                else -> return Minus(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
            }
        }
        ll.firstIndexOrNull { it == "*" || it == "/" }?.let {
            when (ll[it]) {
                "*" -> return Time(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
                else -> return Divide(
                    parseExpression(ll.subList(0, it)),
                    parseExpression(ll.subList(it + 1, ll.size))
                )
            }
        }
        ll.firstIndexOrNull { it == "^" }?.let {
            return Pow(
                parseExpression(ll.subList(0, it)),
                parseExpression(ll.subList(it + 1, ll.size))
            )
        }
        throw IllegalArgumentException("$ll cannot reach this line")
    }

}

