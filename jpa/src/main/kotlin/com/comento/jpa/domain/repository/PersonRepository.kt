package com.comento.jpa.domain.repository

import com.comento.jpa.domain.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Int> {
}