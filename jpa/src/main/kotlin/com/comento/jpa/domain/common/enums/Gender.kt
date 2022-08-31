package com.comento.jpa.domain.common.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class Gender(@JsonValue val genderStr: String) {
    UNKNOWN("UnKnown"),
    MALE("Male"),
    FEMALE("Female");

    companion object {
        @JsonCreator
        operator fun invoke(genderStr: String?) =  values().find { it.genderStr === genderStr } ?: UNKNOWN

    }
}
