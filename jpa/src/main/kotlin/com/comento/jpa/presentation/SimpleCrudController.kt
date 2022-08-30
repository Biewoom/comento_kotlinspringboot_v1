package com.comento.jpa.presentation

import com.comento.jpa.domain.CompanyNotFoundException
import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.service.CompanyService
import com.comento.jpa.service.CountryService
import com.comento.jpa.service.PersonService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/simple-crud")
class SimpleCrudController(
    private val companyService: CompanyService,
    private val countryService: CountryService,
    private val personService: PersonService
) {

    @Operation(summary = "Get a Capital city by it's countryName")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a Capital city",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "Tokyo"))]),
        ApiResponse(responseCode = "404", description = "Country Not Found", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "`France` Cannot be Found" ))])
    ])
    @GetMapping("/countries/{countryName}/capital-city")
    fun getCapitalCityByCountry(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok().body(countryService.findCapitalCityByCountryName(countryName))
        } catch (e: CountryNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @Operation(summary = "Get Companies by it's countryName")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Companies",
            content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = CompanyDto::class)))]),
        ApiResponse(responseCode = "404", description = "Company Not Found", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "`France` Cannot be Found" ))])
    ])
    @GetMapping("/companies/country/{countryName}")
    fun getCompaniesByCountry(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try {
            ResponseEntity.ok().body(companyService.findCompaniesByCountryName(countryName))
        } catch (e: CompanyNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

}