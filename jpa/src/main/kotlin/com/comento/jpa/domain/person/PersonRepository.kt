package com.comento.jpa.domain.person

import com.comento.jpa.domain.common.enums.Gender
import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, Long> {

    fun findPeopleByAgeLessThanEqual(age: Int): List<Person>

    fun findPeopleByGenderAndAgeNotNull(gender: Gender): List<Person>

}