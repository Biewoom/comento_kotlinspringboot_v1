package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.FilterRequest
import com.comento.dbless.presentation.dto.Person
import com.comento.dbless.presentation.dto.Persons
import com.comento.dbless.service.PersonListService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/persons")
class PersonListController(
    private val personListService: PersonListService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/sort")
    fun sortPersons(@RequestBody persons: Persons): List<Person> {
        logger.info("request: $persons")
        return personListService.sortPersons(persons)
    }

    @PostMapping("/filter")
    fun changePersons(@RequestBody filterRequests: FilterRequest): List<Person> {
        logger.info("request: $filterRequests")
        return personListService.filterPersons(filterRequests)
    }
}