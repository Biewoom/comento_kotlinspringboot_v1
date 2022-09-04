package com.comento.dbless.presentation.dto

data class Cutoff(
    val ageCutoff: Int?,
    val heightCutoff: Int?,
    val except: List<String>?,
    val persons: List<Person>
)