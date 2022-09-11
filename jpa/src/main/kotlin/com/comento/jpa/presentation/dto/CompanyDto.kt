package com.comento.jpa.presentation.dto

import com.comento.jpa.domain.YMD
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CompanyDto(
    val foundingDate: YMD,
    val name: String,
    val country: String
)

