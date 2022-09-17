package com.comento.jpa.common.enum

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

// Jackson 이용하여 enum 직렬화 https://smelting.tistory.com/68
enum class Gender(@JsonValue val genderStr: String) {
    UNKNOWN(""),
    MALE("Male"),
    FEMALE("Female");

    companion object {
        @JsonCreator
        operator fun invoke(genderStr: String?) = values().find { it.genderStr === genderStr } ?: UNKNOWN
    }
}