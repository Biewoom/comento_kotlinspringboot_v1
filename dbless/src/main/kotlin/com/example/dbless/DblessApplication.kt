package com.example.dbless

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DblessApplication

fun main(args: Array<String>) {
	runApplication<DblessApplication>(*args)
}
