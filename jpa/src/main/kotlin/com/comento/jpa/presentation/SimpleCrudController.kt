package com.comento.jpa.presentation

import com.comento.jpa.service.CountryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/simple-crud")
class SimpleCrudController(
    private val countryService: CountryService
) {

    @GetMapping("/countries/{countryName}/capital-city")
    fun searchCapitalCity(@PathVariable("countryName") countryName: String): ResponseEntity<String> {
        return try {
            ResponseEntity.ok().body(countryService.findCapitalCityByName(countryName))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
        }
    }
}