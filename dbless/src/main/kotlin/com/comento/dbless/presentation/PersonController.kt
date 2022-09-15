package com.comento.dbless.presentation

import com.comento.dbless.logger
import com.comento.dbless.presentation.dto.Cutoff
import com.comento.dbless.presentation.dto.Person
import com.comento.dbless.presentation.dto.Persons
import com.comento.dbless.service.PersonListService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.apache.logging.log4j.message.Message
import org.slf4j.MDC
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/persons")
class PersonController(private val personListService: PersonListService) {
    @Operation(summary = "Sort people") // 1. Operation
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]), // 2. ApiResponse
        ApiResponse(responseCode = "400", description = "IllegalArgumentException", content = [Content()]),
        ApiResponse(responseCode = "500", description = "Unknown Exception", content = [Content()])
    ])
    @PostMapping("/sort")
    fun sortPersons(@RequestBody people: Persons): ResponseEntity<*>{
        return try {
            MDC.clear()
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.info { "people: $people"}
            ResponseEntity.status(HttpStatus.OK).body(personListService.sortPersons(people))
        } catch(e: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
        } catch(e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error] If Under Emergency, Please contact by emil likemin014@gmail.com")
        }
    }
    @Operation(summary = "Filter people by cutoff") // 1. Operation
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Message::class))]), // 2. ApiResponse
        ApiResponse(responseCode = "400", description = "IllegalArgumentException", content = [Content()]),
        ApiResponse(responseCode = "500", description = "Unknown Exception", content = [Content()])
    ])
    @PostMapping("/filter")
    fun filterPersons(@RequestBody cutoff: Cutoff):  ResponseEntity<*> {
        return try {
            MDC.clear()
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.info { "people: $cutoff"}
            ResponseEntity.status(HttpStatus.OK).body(personListService.filterPersons(cutoff))
        } catch(e: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cause: ${e.message}")
        } catch(e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Unknown Error] If Under Emergency, Please contact by emil likemin014@gmail.com")
        }
    }
}