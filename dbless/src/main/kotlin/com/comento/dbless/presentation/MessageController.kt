package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.Message
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MessageController {

    // Rest Controller + Mapping Annotation 으로 API 구성

    /**
     * "hello"를 출력
     */
    @GetMapping("/hello")
    fun sayHell(): String {
        return "hello"
    }

    /**
     * 메시지 목록 출력
     */
    @GetMapping("/messages/all")
    fun getAllMessages() = messages

    /**
     * 하나의 메시지를 출력
     */
    @Operation(summary = "Get a message by it's id") // 1. Operation
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Found the Message",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]
            ), // 2. ApiResponse
            ApiResponse(responseCode = "400", description = "Invalid Id supplied", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Message Not Found", content = [Content()])
        ]
    )
    @GetMapping("/messages/{id}")
    fun getOneMessage(@PathVariable("id") id: String) = messages.find { it.id == id }

    /**
     * 메시지를 저장
     */
    @PostMapping("/messages")
    fun sendMessage(@RequestBody message: Message): Message {
        messages.add(message)
        messages.sortWith { message1, message2 -> message1.id.compareTo(message2.id) }
        return message
    }

    /**
     * 해당 ID의 메시지를 삭제
     */
    @DeleteMapping("/messages/{id}")
    fun deleteOneMessage(@PathVariable("id") id: String): String {
        messages.removeIf { it.id == id }
        return id
    }

    /**
     * 메시지를 삭제하고 새로 추가 ( 수정 )
     */
    @PutMapping("/messages")
    fun putOneMessage(@RequestBody message: Message): Message {
        messages.removeIf { it.id == message.id }
        messages.add(message)
        messages.sortWith { message1, message2 -> message1.id.compareTo(message2.id) }
        return message
    }

    // Sample Data Set
    companion object {
        val messages = mutableListOf(
            Message("id1", "Good"),
            Message("id2", "Bad"),
            Message("id3", "Awesome"),
            Message("id4", "Great"),
            Message("id5", "Amazing")
        )
    }
}