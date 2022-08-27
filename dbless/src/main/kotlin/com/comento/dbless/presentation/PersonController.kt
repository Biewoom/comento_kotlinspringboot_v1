package com.comento.dbless.presentation

import com.comento.dbless.presentation.dto.Cutoff
import com.comento.dbless.presentation.dto.Person
import com.comento.dbless.presentation.dto.Persons
import com.comento.dbless.service.PersonListService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/persons")
class PersonController(private val personListService: PersonListService) {

    @PostMapping("/sort")
    fun sortPersons(@RequestBody people: Persons): List<Person>{
        return personListService.sortPersons(people)
    }

    @PostMapping("/filter")
    fun filterPersons(@RequestBody cutoff: Cutoff): List<Person> {
        return personListService.filterPersons(cutoff)
    }
}