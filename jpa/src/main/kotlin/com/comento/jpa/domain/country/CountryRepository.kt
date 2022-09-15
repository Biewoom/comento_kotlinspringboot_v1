package com.comento.jpa.domain.country

import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<Country, Long> {
    fun getCountryByName(name: String): Country?
}