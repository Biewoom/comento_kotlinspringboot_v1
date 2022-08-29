package com.comento.jpa.presentation

import com.comento.jpa.domain.company.Company
import com.comento.jpa.domain.person.Gender
import com.comento.jpa.domain.person.Person
import com.comento.jpa.service.CompanyService
import com.comento.jpa.service.CountryService
import com.comento.jpa.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/simple")
class CompanyController(
    private val companyService: CompanyService,
    private val countryService: CountryService,
    private val personService: PersonService
) {

    @GetMapping("/country/{countryName}")
    fun getCompanyNamesByCountry(@PathVariable("countryName") countryName: String): List<Company> {
        return companyService.findCompaniesByCountry(countryName)
    }

    @GetMapping("/country/id/{id}")
    fun getCompanyNamesById(@PathVariable("id") id: Long): Company {
        return companyService.findCompanyById(id)
    }

    @PostMapping("/company")
    fun makeCompany(@RequestBody companyRequest: CompanyRequest){
        companyService.makeCompany(companyRequest)
    }


}