package com.comento.jpa.presentation.dto

import com.comento.jpa.common.enum.Gender
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BlindDateDto(
    val name: String,
    val gender: Gender,
    val age: Int,
    val height: Int?,
    val weight: Int?,
    val company: String?,
    val country: String
)