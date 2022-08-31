package com.comento.jpa.utils

import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.presentation.PersonDto
import com.comento.jpa.toPrettyJson
import org.junit.jupiter.api.Test

fun getRangeIntWithNullable(start: Int, end: Int) = (start..end).toList() + (start..end).toList()
fun getRangeIntWithNullable(start: Long, end: Long) = (start..end).toList() + (start..end).toList()

val DYNAMIC_DUMMY: PersonDto
    get() = PersonDto(
            personId =  getRangeIntWithNullable(0, 30).random(),
            age = getRangeIntWithNullable(0, 100).random(),
            height = getRangeIntWithNullable(150, 200).random(),
            weight = getRangeIntWithNullable(30, 200).random(),
            name = ("허승기, 설지태, 이상우, 사공서준, 예인영, 남희아, 김병욱, 신해빈, 문병희, 심대철, 예효주, 하선빈, 탁은우, 조인욱, 예철민, 윤시환, 탁창현, 설경옥, 추혜성, 문남일" +
                    "Agatha, Aileen, cupboard, inch, loyalty, imitate, flatten, past").split(", ").random(),
            gender = listOf("Male", "Female", null).random()?.let { Gender(it) },
            isMarried = listOf(true, false, null).random(),
            company = listOf("GOOGLE", "META", "KAKAO", "DAEWO", "SHELL", "NAVER", null, null).random(),
            country = listOf("USA", "KOREA", "CHINA", "JAPAN", "RUSSIA", "TAIWAN", "VIETNAM" , "IRELAND", "ENGLAND", "CANADA").random()
    )


class Generator {

    @Test
    fun generate(){
        val persons = (1..10).map { DYNAMIC_DUMMY }

        println(persons.toPrettyJson())
    }
}

