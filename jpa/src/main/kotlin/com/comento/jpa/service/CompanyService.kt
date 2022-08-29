package com.comento.jpa.service

import com.comento.jpa.domain.company.Company
import com.comento.jpa.domain.company.CompanyRepository
import com.comento.jpa.presentation.CompanyRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
) {

    fun findCompanyById(id: Long): Company {
        return companyRepository.findByIdOrNull(id) ?: throw RuntimeException("Not Found company")
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

    fun findCompaniesByCountry(countryName: String): List<Company> {
        return companyRepository.findCompaniesByCountry(countryName)
    }


}