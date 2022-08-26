package com.comento.dbless.presentation.dto

data class CalculatorRequestDto(
    val expression: String,
    val round: Int
)