package com.comento.jpa.service

import com.comento.jpa.domain.Company
import com.comento.jpa.domain.repository.CompanyRepository
import com.comento.jpa.presentation.dto.CompanyDto
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {

    fun findCompanyDtoListByCountry(country: String): List<CompanyDto> {
        val companyList = companyRepository.findByCountryOrderByFoundingDate(country.uppercase());
        if (companyList.isEmpty()) throw IllegalArgumentException("`$country` is not value")

        return companyList.map(Company::toDto)
    }
}
