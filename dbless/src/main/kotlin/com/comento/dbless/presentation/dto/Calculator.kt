package com.comento.dbless.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema

data class Calculator(
    @Schema(title = "expression", example = "1 + 10 * 5")  val expression: String,
    @Schema(title = "round", description = "소숫점 반올림 자리", example = "1")  val round: Int
)