package com.comento.dbless.presentation.dto

import com.comento.dbless.domain.Person

data class PersonsFilterRequestDto(
    val persons: List<Person>,
    val ageCutoff: Int?,
    val heightCutoff: Int?,
    val except: List<String>?
)