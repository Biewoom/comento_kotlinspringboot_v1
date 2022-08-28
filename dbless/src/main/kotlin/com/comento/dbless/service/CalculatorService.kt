package com.comento.dbless.service

import com.comento.dbless.getDecimalPoint
import com.comento.dbless.toNumber
import com.comento.dbless.toRound
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Integer.max
import kotlin.math.pow
import kotlin.random.Random

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

    fun calculate(expression: String, round: Int): Double {
        return parseExpression(expression).toRound(round)
    }

    private fun parseExpression(expression: String): Double {
        val filteredExpression = expression.filter { !it.isWhitespace() }.toList()
        val calculatingBlocks = mutableListOf<String>()
        var index = 0
        while(index < filteredExpression.size){
            if(filteredExpression.get(index) in listOf('+', '-', '*', '/', '^')){
                calculatingBlocks.add(filteredExpression[index].toString())
                index += 1
            }else{
                val numberBlock = filteredExpression.subList(fromIndex = index, toIndex = filteredExpression.size).takeWhile { it.isDigit() }.joinToString("")
                calculatingBlocks.add(numberBlock)
                index += numberBlock.length
            }
        }
        logger.info("calculatingBlocks: $calculatingBlocks")
        return calculateExpression(calculatingBlocks.toList())
    }

    private fun calculateExpression(expressionChars: List<String>):Double{
        expressionChars.firstOrNull { it in listOf("+", "-") }?.let {
            val index = expressionChars.indexOf(it)
            if(it=="+"){
                return calculateExpression(expressionChars.subList(0, index)) + calculateExpression(expressionChars.subList(index+1, expressionChars.size))
            }else{
                return calculateExpression(expressionChars.subList(0, index)) - calculateExpression(expressionChars.subList(index+1, expressionChars.size))
            }
        }

        expressionChars.firstOrNull { it in listOf("*", "/", "^") }?.let {
            val index = expressionChars.indexOf(it)
            if(it=="*"){
                return calculateExpression(expressionChars.subList(0, index)) * calculateExpression(expressionChars.subList(index+1, expressionChars.size))
            }else if(it=="/"){
                return calculateExpression(expressionChars.subList(0, index)) / calculateExpression(expressionChars.subList(index+1, expressionChars.size))
            }else {
                return calculateExpression(expressionChars.subList(0, index)).pow(calculateExpression(expressionChars.subList(index+1, expressionChars.size)))
            }
        }

        if(expressionChars.size==1){
            return expressionChars.first().toDouble()
        }

        throw RuntimeException()

    }

}