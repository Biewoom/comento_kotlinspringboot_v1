package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.Message
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
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

    @Operation(summary = "Get all messages")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found All messages",
            content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = Message::class)))]
        )
    ])
    @GetMapping("/messages/all")
    fun getAllMessages() = messages

    @Operation(summary = "Get a message by it's id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found the Message",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]),
        ApiResponse(responseCode = "400", description = "Invalid Id supplied", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Message Not Found", content = [Content()])
    ])
    @GetMapping("/messages/{id}")
    fun getOneMessage(@Parameter(required = true, description = "Id of Message to be searched", example = "id1") @PathVariable("id") id: String) = messages.find { it.id == id }

    @Operation(summary = "Insert a New Message")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Inserting a new message is Succeed",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]
        ),
        ApiResponse(responseCode = "400", description = "Inserting A new Message is failed",
            content = [Content()]
        )
    ])
    @PostMapping("/messages")
    fun sendMessage(@RequestBody message: Message): Message {
        messages.add(message)
        messages.sortWith { message1, message2 -> message1.id.compareTo(message2.id) }
        return message
    }

    @Operation(summary = "Delete a existed Message by it's id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Deleting a Existed Message is Succeed",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "id1"))]
        )
    ])
    @DeleteMapping("/messages/{id}")
    fun deleteOneMessage(@Parameter(required = true, description = "Id of Message to be removed", example = "id1") @PathVariable("id") id: String): String {
        messages.removeIf { it.id == id }
        return id
    }

    @Operation(summary = "Update a exited Message Or Insert a new Message")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Update Message is Succeed",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]
        ),
        ApiResponse(responseCode = "400", description = "Update Message is failed",
            content = [Content()]
        )
    ])
    @PutMapping("/messages")
    fun putOneMessage(@RequestBody message: Message): Message {
        messages.removeIf { it.id == message.id }
        messages.add(message)
        messages.sortWith { message1, message2 -> message1.id.compareTo(message2.id) }
        return message
    }

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