package com.comento.first

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstApplication

fun main(args: Array<String>) {
    runApplication<FirstApplication>(*args)
}
