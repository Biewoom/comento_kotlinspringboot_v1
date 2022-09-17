package com.comento.jpa.domain

data class BlindDateNotFoundException(override val message: String): RuntimeException(message)