package com.comento.second

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecondApplication

fun main(args: Array<String>) {
    runApplication<SecondApplication>(*args)
}
