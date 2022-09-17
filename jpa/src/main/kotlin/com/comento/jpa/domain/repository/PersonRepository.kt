package com.comento.jpa.domain.repository

import com.comento.jpa.common.enum.Gender
import com.comento.jpa.domain.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Int> {

    fun findByGenderAndAgeNotNull(gender: Gender): List<Person>
}