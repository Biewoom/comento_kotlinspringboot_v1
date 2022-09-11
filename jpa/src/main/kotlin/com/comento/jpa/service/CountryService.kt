package com.comento.jpa.service

import com.comento.jpa.domain.repository.CountryRepository
import org.springframework.stereotype.Service

@Service
class CountryService(
    private val countryRepository: CountryRepository
) {
    fun findCapitalCityByName(name: String): String{
        val country = countryRepository.findByName(name.uppercase()) ?: throw IllegalArgumentException("'$name' is not value")
        return country.capitalCity;
    }

}