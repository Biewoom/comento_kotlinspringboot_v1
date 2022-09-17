package com.comento.jpa.service

import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.domain.person.Person
import com.comento.jpa.domain.person.PersonRepository
import com.comento.jpa.presentation.PersonDto
import com.comento.jpa.presentation.ResultDto
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PersonServiceTest {

    private val personRepositoryMock = mockk<PersonRepository>(relaxed = true)
    private val personServiceSut = PersonService(personRepositoryMock)

    @DisplayName("registerOrSavePersons stub 성공 테스트 ")
    @Test
    fun `test registerOrSavePersons `() {
        //given
        every {
            personRepositoryMock.existsBy_id(1)
        } returns true
        every {
            personRepositoryMock.existsBy_id(2)
        } returns false

        every {
            personRepositoryMock.save(Person("예철민", Gender.FEMALE, "CHINA")
                .apply {
                    age = 48
                    height = 196
                    weight = 144
                    isMarried = null
                    company = "DAEWO"
                    updateId(1)
                }).id
        } returns 1

        every {
            personRepositoryMock.save(Person("탁은우", Gender.UNKNOWN, "VIETNAM")
                .apply {
                    age = 3
                    height = 168
                    weight = 192
                    isMarried = null
                    company = "SHELL"
                    updateId(2)
                }
            ).id
        } returns 2

        val expectedResultDto = ResultDto(
            listOf(0, 1),
            listOf(1, 2)
        )

        //when

        val res = personServiceSut.registerOrSavePersons(
            listOf(
                PersonDto(
                    personId = 1,
                    age = 48,
                    height = 196,
                    weight = 144,
                    name = "예철민",
                    gender = Gender.FEMALE,
                    isMarried = null,
                    company = "DAEWO",
                    country = "CHINA"
                ),
                PersonDto(
                    personId = 2,
                    age = 3,
                    height = 168,
                    weight = 192,
                    name = "탁은우",
                    gender = null,
                    isMarried = null,
                    company = "SHELL",
                    country = "VIETNAM"
                )
            )
        )

        //then
        res shouldBe expectedResultDto
    }
}