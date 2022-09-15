package com.comento.jpa.service

import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.domain.country.CountryRepository
import com.comento.jpa.logger
import org.springframework.stereotype.Service

@Service
class CountryService(private val countryRepository: CountryRepository) {
    fun getCapital(countryName: String): String{
        logger.info("CountryService ${countryName.uppercase()}")
        val country = countryRepository.getCountryByName(countryName.uppercase()) ?: throw CountryNotFoundException("`$countryName` Cannot be found")
        logger.info("country $country")
        return country.capital
    }

}