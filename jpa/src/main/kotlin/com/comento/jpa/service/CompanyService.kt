package com.comento.jpa.service

import com.comento.jpa.domain.CompanyNotFoundException
import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.domain.company.CompanyRepository
import com.comento.jpa.domain.country.Company
import com.comento.jpa.domain.country.CountryRepository
import com.comento.jpa.logger
import com.comento.jpa.presentation.CompanyRequest
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {
    fun getCompanyList(countryName: String): List<Company> {
        val companies = companyRepository.findCompaniesByCountry(countryName.uppercase())
        if( companies.isEmpty()){
            throw CompanyNotFoundException("No company for $countryName ")
        }
        return companies.sortedBy { it.foundingDate }
    }

    fun makeCompany(companyRequest: CompanyRequest) {
        val (name, foundingDate, country) = companyRequest
        val company = Company(
            name = name,
            foundingDate = foundingDate,
            country = country
        )
        companyRepository.save(company)
    }
}