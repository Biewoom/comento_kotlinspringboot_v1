package com.comento.jpa.service

import com.comento.jpa.domain.CompanyNotFoundException
import com.comento.jpa.domain.company.Company
import com.comento.jpa.domain.company.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
) {

    fun findCompaniesByCountryName(countryName: String): List<Company> {
        val companies = companyRepository.findCompaniesByCountry(countryName.uppercase())
        if ( companies.isEmpty() ) throw CompanyNotFoundException("`$countryName` Was Not Found")

        return companies.sortedBy { it.foundingDate }
    }


}