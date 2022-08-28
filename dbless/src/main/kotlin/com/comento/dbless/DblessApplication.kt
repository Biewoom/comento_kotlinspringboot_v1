package com.comento.dbless

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.math.pow
import kotlin.math.roundToLong

internal fun String.toNumber(): Number = toLongOrNull() ?: toDoubleOrNull() ?: 0
internal fun Double.toRound(int: Int) = (this * (10.0.pow(int))).roundToLong() / ( 10.0.pow(int) )

internal val logger = KotlinLogging.logger {}

internal typealias SRequestBody = io.swagger.v3.oas.annotations.parameters.RequestBody

@OpenAPIDefinition(
    info = Info(
        title = "Comento Kotlin & SpringBoot Swagger Doc",
        version = "\${springdoc.version}",
        description = "Comento API description",
        contact = Contact(name = "comento", url = "https://www.notion.so/Kotlin-Spring-26f59ad056e14a94ac83235984ceb4f4", email = "likemin0142@gmail.com"),
        license = License(name = "MIT license", url = "https://www.mit.edu/~amini/LICENSE.md"),
    )
)
@SpringBootApplication
class DblessApplication
fun main(args: Array<String>) {
    runApplication<DblessApplication>(*args)
}
