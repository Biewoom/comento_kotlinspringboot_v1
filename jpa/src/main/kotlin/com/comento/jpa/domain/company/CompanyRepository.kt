package com.comento.jpa.domain.company

import com.comento.jpa.domain.country.Company
import org.springframework.data.repository.CrudRepository

interface CompanyRepository: CrudRepository<Company, Long> {
    fun findCompaniesByCountry(country: String): List<Company>
}