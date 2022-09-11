package com.comento.jpa.domain

data class CountryNotFoundException(override val message: String): RuntimeException(message)