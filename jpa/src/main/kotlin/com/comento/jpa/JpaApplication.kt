package com.comento.jpa

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


val logger = KotlinLogging.logger { }

val OBJECT_MAPPER = jacksonObjectMapper()

fun Any.toJson() = OBJECT_MAPPER.writeValueAsString(this)
fun Any.toPrettyJson() = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(this)

@SpringBootApplication
class JpaApplication
fun main(args: Array<String>) {
	runApplication<JpaApplication>(*args)
}
