package com.comento.jpa.presentation

import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.logger
import com.comento.jpa.service.CompanyService
import com.comento.jpa.service.CountryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/simple-crud")
class SimpleCrudControlle(private val countryService: CountryService,
                          private val companyService: CompanyService) {

    @Operation(summary="Get capital city of given country")
    @ApiResponses(value =[
        ApiResponse(responseCode = "200", description = "Found a Capital city", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "Tokyo"))]),
        ApiResponse(responseCode = "404", description = "Country Not Found", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "`France` Cannot be Found" ))])
    ])
    @GetMapping("/countries/{countryName}/capital-city")
    fun getCapitalCity(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try{
            ResponseEntity.ok().body(countryService.getCapital(countryName))
        } catch (e: CountryNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @Operation(summary="Get the list of companies from given country")
    @ApiResponses(value =[
        ApiResponse(responseCode = "200", description = "Found companies", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "Tokyo"))]),
        ApiResponse(responseCode = "404", description = "Company Not Found", content = [Content(mediaType = "application/json", schema = Schema(implementation = String::class, example = "`France` Cannot be Found" ))])
    ])
    @GetMapping("/companies/country/{countryName}")
    fun getCompanyList(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        return try{
            ResponseEntity.ok().body(companyService.getCompanyList(countryName))
        } catch (e: CountryNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

    @PostMapping("/company")
    fun makeCompany(@RequestBody companyRequest: CompanyRequest){
        companyService.makeCompany(companyRequest)
    }

}