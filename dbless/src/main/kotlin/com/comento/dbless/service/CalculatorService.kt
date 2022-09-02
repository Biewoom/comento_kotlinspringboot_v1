package com.comento.dbless.service

import com.comento.dbless.domain.Divide
import com.comento.dbless.domain.Expr
import com.comento.dbless.domain.Minus
import com.comento.dbless.domain.Pow
import com.comento.dbless.domain.Sum
import com.comento.dbless.domain.Time
import com.comento.dbless.getDecimalPoint
import com.comento.dbless.toNumber
import com.comento.dbless.toRound
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Integer.max
import kotlin.math.pow
import kotlin.random.Random

private inline fun <T> List<T>.lastIndexOrNull(predicate: (T) -> Boolean): Int? = run {
    val res = this.indexOfLast(predicate)
    if (res == -1) null
    else res
}
@Service
class CalculatorService {
    private val logger = LoggerFactory.getLogger(javaClass)

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
            if (it in listOf('+', '-', '*', '/', '^')) {
                res.add(num)
                res.add(it.toString())
                num = ""
            } else {
                if (!it.isDigit()) throw IllegalArgumentException("`$it` should be digit ")
                num += it
            }
        }
        res.add(num)
        return res.toList()
    }

    private fun parseExpression(ll: List<String>): Expr {
        if (ll.size == 1) return Expr.Num(ll[0].toDouble())

        ll.lastIndexOrNull { it == "+" || it == "-" }?.let {
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
        ll.lastIndexOrNull { it == "*" || it == "/" }?.let {
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
        ll.lastIndexOrNull { it == "^" }?.let {
            return Pow(
                parseExpression(ll.subList(0, it)),
                parseExpression(ll.subList(it + 1, ll.size))
            )
        }
        throw IllegalArgumentException("$ll cannot reach this line")
    }

}