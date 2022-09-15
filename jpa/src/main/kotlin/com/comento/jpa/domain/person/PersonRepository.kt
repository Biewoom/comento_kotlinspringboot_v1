package com.comento.jpa.domain.person

import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, Long> {
    fun existsBy_id(_id: Int): Boolean
}