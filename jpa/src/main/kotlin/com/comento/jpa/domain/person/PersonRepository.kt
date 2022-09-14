package com.comento.jpa.domain.person

import com.comento.jpa.domain.common.enums.Gender
import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, Long> {

    fun findPeopleByGenderAndAgeNotNull(gender: Gender): List<Person>

    fun existsBy_id(_id: Long): Boolean
}