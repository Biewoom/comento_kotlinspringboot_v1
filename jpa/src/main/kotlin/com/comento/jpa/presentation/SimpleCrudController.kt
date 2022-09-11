package com.comento.jpa.presentation

import com.comento.jpa.domain.CountryNotFoundException
import com.comento.jpa.logger
import com.comento.jpa.service.CountryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/simple-crud")
class SimpleCrudControlle(private val countryService: CountryService) {

    @GetMapping("/countries/{countryName}/capital-city")
    fun getCapitalCity(@PathVariable("countryName") countryName: String): ResponseEntity<*> {
        logger.info("SimpleCrudController $countryName")
        return try{
            ResponseEntity.ok().body(countryService.getCapital(countryName))
        } catch (e: CountryNotFoundException){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }

}