package com.comento.jpa.domain.company


import org.springframework.data.repository.CrudRepository

interface CompanyRepository: CrudRepository<Company, Long> {
    fun findCompaniesByCountry(country: String): List<Company>
}