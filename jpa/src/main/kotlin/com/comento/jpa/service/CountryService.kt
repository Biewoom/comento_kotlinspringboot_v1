package com.comento.jpa.service

import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.domain.country.Country
import com.comento.jpa.domain.country.CountryRepository
import com.comento.jpa.domain.person.Gender
import com.comento.jpa.presentation.CountryRequest
import org.springframework.stereotype.Service

@Service
class CountryService(
    private val countryRepository: CountryRepository
) {

    fun makeCountry(countryRequest: CountryRequest) {
        val (zipCode, name, capitalCity) = countryRequest

        val country = Country(
            zipCode = zipCode,
            name = name,
            capitalCity = capitalCity
        )
        Gender.FEMALE
        countryRepository.save(country)
    }

    fun findCapitalCityByCountryName(countryName: String): String {
        val country = countryRepository.findCountryByName(countryName.uppercase()) ?: throw CountryNotFoundException("`$countryName` Cannot be Found")
        return country.capitalCity
    }
}