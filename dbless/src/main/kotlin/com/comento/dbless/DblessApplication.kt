package com.comento.dbless

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    info = Info(
        title = "Comento Kotlin & SpringBoot Swagger Doc", // 1. title
        version = "\${springdoc.version}", // 2. version
        description = "Comento API description", // 3. description
        contact = Contact(name = "comento", url = "https://www.notion.so/Kotlin-Spring-26f59ad056e14a94ac83235984ceb4f4", email = "likemin0142@gmail.com"), // 4. contact
        license = License(name = "MIT license", url = "https://www.mit.edu/~amini/LICENSE.md"), // 5. license
    )
)

@SpringBootApplication
class DblessApplication

fun main(args: Array<String>) {
    runApplication<DblessApplication>(*args)
}
