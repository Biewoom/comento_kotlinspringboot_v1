package com.comento.jpa.presentation.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GeneratePersonsResponseDto (
    val resultTypes: List<Int>,
    val personIds: List<Int>
)