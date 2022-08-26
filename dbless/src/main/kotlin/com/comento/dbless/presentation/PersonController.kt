package com.comento.dbless.presentation

import com.comento.dbless.domain.Person
import com.comento.dbless.presentation.dto.PersonsSortRequestDto
import com.comento.dbless.service.PersonService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/persons")
class PersonController (val personService: PersonService){

    /**
     * Person 리스트, Sorting 기준, Sorting Order을 RequestBody 로 받고,
     * 정렬된 Person 리스트 를 Client에게 돌려주는 API
     */
    @PostMapping("/sort")
    fun sortPersons(@RequestBody requestBody: PersonsSortRequestDto): List<Person>{
        return personService.sortPersonLogic(requestBody.persons,requestBody.sortBy,requestBody.sortOrder);
    }
}