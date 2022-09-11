package com.comento.jpa.domain.repository

import com.comento.jpa.domain.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Long> {

    fun findByName(name: String): Country?
}