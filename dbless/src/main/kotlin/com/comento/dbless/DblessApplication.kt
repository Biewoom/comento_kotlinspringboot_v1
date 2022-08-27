package com.comento.dbless

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.math.pow
import kotlin.math.roundToLong

@SpringBootApplication
class DblessApplication

internal fun String.toNumber(): Number = toLongOrNull() ?: toDoubleOrNull() ?: 0

internal fun String.getDecimalPoint() = this.split(".").getOrNull(1)?.length ?: 0

internal fun Double.toRound(int: Int) = (this * (10.0.pow(int))).roundToLong() / ( 10.0.pow(int) )

fun main(args: Array<String>) {
    runApplication<DblessApplication>(*args)
}
