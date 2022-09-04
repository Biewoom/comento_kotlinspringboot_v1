package com.comento.dbless.presentation.dto

data class Persons(
    val persons: List<Person>,
    val sortBy: String,
    val sortOrder: String
)