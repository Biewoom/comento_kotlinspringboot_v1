package com.comento.dbless.service

import com.comento.dbless.service.presentation.dto.FilterRequest
import com.comento.dbless.service.presentation.dto.Person
import com.comento.dbless.service.presentation.dto.Persons
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PersonListService {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun sortPersons(persons: Persons): List<Person> {
        val (personList, sortBy, sortOrder) = persons

        val personListAfterSorted = when(sortBy){
            "id" -> personList.sortedBy { it.id }
            "age" -> personList.sortedBy { it.age }
            "height" -> personList.sortedBy { it.height }
            "name" -> personList.sortedBy { it.name }
            else -> throw IllegalArgumentException("`$sortBy` is not Supported ")
        }

        return when(sortOrder) {
            "asc" -> personListAfterSorted
            "des" -> personListAfterSorted.reversed()
            else -> throw IllegalArgumentException("`$sortOrder` is not Supported ")
        }
    }

    fun filterPersons(filterRequest: FilterRequest): List<Person> {

        val (ageCutoff, except, heightCutoff, persons) = filterRequest
        val ageStandard = ageCutoff ?: 0
        val heightStandard = heightCutoff ?: 0
        val exceptStandard = except ?: emptyList()

        logger.info("ageStandard: $ageStandard, heightStandard: $heightStandard")

        return persons
                .filter { it.age >= ageStandard && it.height >= heightStandard }
                .filter { it.id !in exceptStandard }
    }
}