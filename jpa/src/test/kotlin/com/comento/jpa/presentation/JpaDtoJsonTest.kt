package com.comento.jpa.presentation

import com.comento.jpa.OBJECT_MAPPER
import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.toSingleStringWithoutSpace
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import io.kotest.matchers.shouldBe

internal class JpaDtoJsonTest {

    @DisplayName("List<PersonDto> 역직렬화 테스트")
    @ParameterizedTest
    @MethodSource("provider_personDtoList")
    fun `test personDtoList deserialization `(pair: Pair<List<PersonDto>, String>) {
        //given
        val (personDtoList, expectedJsonString) = pair

        val personDtoMutableList = personDtoList.toMutableList()
        val per = personDtoList.toMutableList()

        //when
        val res = OBJECT_MAPPER.readValue(expectedJsonString, mutableListOf<PersonDto>().javaClass)

        //then

        // 같은 mutableList인데 왜 안될까...
        res shouldBe per
    }

    @DisplayName("ResultDto 직렬화 테스트")
    @ParameterizedTest
    @MethodSource("provider_resultDto")
    fun `test resultDto serialization `(pair: Pair<ResultDto, String>) {
        //given
        val (resultDto, expectedJsonString) = pair

        //when
        val res = OBJECT_MAPPER.writeValueAsString(resultDto)

        //then
        res shouldBe expectedJsonString

    }

    companion object {
        @JvmStatic
        fun provider_resultDto() = listOf(
            Pair(
                ResultDto(
                    listOf(
                        1, 2, 3
                    ), listOf(4, 5, 6)
                ),
                """
                    {
                    "result_types" : [1,2,3],
                    "person_ids" : [4,5,6]
                    }
                """.toSingleStringWithoutSpace()
            )
        )

        @JvmStatic
        fun provider_personDtoList() = listOf(
            Pair(
                listOf(
                    PersonDto(
                        1,
                        48,
                        196,
                        144,
                        "예철민",
                        Gender.FEMALE,
                        null,
                        "DAEWO",
                        "CHINA"
                    ),
                    PersonDto(
                        3,
                        3,
                        168,
                        192,
                        "탁은우",
                        null,
                        null,
                        "SHELL",
                        "VIETNAM"
                    )
                ),
                """
                    [ {
                      "person_id" : 1,
                      "age" : 48,
                      "height" : 196,
                      "weight" : 144,
                      "name" : "예철민",
                      "gender" : "Female",
                      "is_married" : null,
                      "company" : "DAEWO",
                      "country" : "CHINA"
                    }, {
                        "person_id" : 3,
                        "age" : 3,
                        "height" : 168,
                        "weight" : 192,
                        "name" : "탁은우",
                        "gender" : null,
                        "is_married" : null,
                        "company" : "SHELL",
                        "country" : "VIETNAM"
                    } ]
                """.toSingleStringWithoutSpace()
            )
        )

    }


}