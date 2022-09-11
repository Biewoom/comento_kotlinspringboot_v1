package com.comento.jpa.presentation

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CompanyRequest (
    val name: String,
    val foundingDate: String,
    val country: String
)

@Schema(title = "회사 DTO")
data class CompanyDto (
    @Schema(title = "회사 이름", example = "Apple") val name: String,
    @Schema(title = "회사 설립 연도", example = "2011-01-22")  val foundingDate: String,
    @Schema(title = "국가", example = "USA") val country: String
)