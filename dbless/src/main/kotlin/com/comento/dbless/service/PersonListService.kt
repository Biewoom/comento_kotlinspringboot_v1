package com.comento.dbless.service

import com.comento.dbless.presentation.dto.Person
import com.comento.dbless.presentation.dto.Persons
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.lang.IllegalArgumentException

@Service
class PersonListService {
    fun sortPersons(@RequestBody persons: Persons): List<Person>{
        val (people, sortBy, sortOrder) = persons
        val sortedPeoople = when(sortBy){
            "id" -> people.sortedBy { it.id }
            "age" -> people.sortedBy { it.age }
            "height" -> people.sortedBy { it.height }
            "name" -> people.sortedBy { it.name }
            else -> throw IllegalArgumentException("wrong sortBy")
        }

        return when(sortOrder){
            "asc" -> sortedPeoople
            "des" -> sortedPeoople.reversed()
            else -> throw IllegalArgumentException("wrong sortOrder")
        }
    }
}