package com.comento.dbless.presentation.dto

data class Persons(
        val persons: List<Person>,
        val sortBy: String,
        val sortOrder: String
)

data class Person(
        val age: Int,
        val height: Int,
        val id: String,
        val name: String
)