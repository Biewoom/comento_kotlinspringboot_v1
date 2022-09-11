package com.comento.jpa.domain

data class CountryNotFoundException(override val message: String): RuntimeException(message)

data class CompanyNotFoundException(override val message: String): RuntimeException(message)