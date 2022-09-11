package com.comento.dbless.presentation.dto

import com.comento.dbless.domain.Person

data class PersonsSortRequestDto (
    val persons : List<Person>,
    val sortBy : String,
    val sortOrder : String
)