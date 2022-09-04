package com.comento.dbless.presentation.dto

data class CalculatorRequest(
    val expression: String,
    val round: Int
)