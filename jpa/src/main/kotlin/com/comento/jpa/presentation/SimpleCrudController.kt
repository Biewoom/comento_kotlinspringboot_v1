package com.comento.jpa.presentation

import com.comento.jpa.presentation.dto.CompanyDto
import com.comento.jpa.presentation.dto.PersonDto
import com.comento.jpa.service.CompanyService
import com.comento.jpa.service.CountryService
import com.comento.jpa.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/simple-crud")
class SimpleCrudController(
    private val countryService: CountryService,
    private val companyService: CompanyService,
    private val personService: PersonService
) {

    @GetMapping("/countries/{countryName}/capital-city")
    fun searchCapitalCity(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok().body(countryService.findCapitalCityByName(countryName))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @GetMapping("/companies/country/{countryName}")
    fun searchCompanyListByCountryName(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok().body(companyService.findCompanyDtoListByCountry(countryName))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @PutMapping("/persons")
    fun generatePersons(@RequestBody request: List<PersonDto>): ResponseEntity<*> {
        return try {
            ResponseEntity.ok().body(personService.upsertPersons(request))
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

}