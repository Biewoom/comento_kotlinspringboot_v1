package com.comento.jpa

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


val logger = KotlinLogging.logger { }
@SpringBootApplication
class JpaApplication
fun main(args: Array<String>) {
	runApplication<JpaApplication>(*args)
}
