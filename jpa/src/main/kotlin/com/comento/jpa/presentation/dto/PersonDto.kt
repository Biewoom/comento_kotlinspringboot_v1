package com.comento.jpa.presentation.dto

import com.comento.jpa.common.enum.Gender
import com.comento.jpa.domain.Person
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PersonDto(
    val personId: Int?,
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val name: String,
    val gender: Gender?,
    val isMarried: Boolean?,
    val company: String?,
    val country: String
) {
    fun toEntity(): Person {
        val (
            personId: Int?,
            age: Int?,
            height: Int?,
            weight: Int?,
            name: String,
            gender: Gender?,
            isMarried: Boolean?,
            company: String?,
            country: String
        ) = this

        val person = Person(personId)
        person.age = age
        person.height = height
        person.weight = weight
        person.name = name
        person.gender = gender ?: Gender.UNKNOWN
        person.isMarried = isMarried ?: false
        person.company = company
        person.country = country
        return person
    }
}
