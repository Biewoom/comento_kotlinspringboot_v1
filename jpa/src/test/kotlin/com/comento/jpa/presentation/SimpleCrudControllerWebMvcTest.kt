package com.comento.jpa.presentation

import com.comento.jpa.OBJECT_MAPPER
import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.service.CompanyService
import com.comento.jpa.service.CountryService
import com.comento.jpa.service.PersonService
import com.comento.jpa.toSingleStringWithoutSpace
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@WebMvcTest(controllers = [SimpleCrudController::class])
internal class SimpleCrudControllerWebMvcTest {

    @MockkBean(relaxed = true)
    private lateinit var companyService: CompanyService

    @MockkBean(relaxed = true)
    private lateinit var countryService: CountryService

    @MockkBean(relaxed = true)
    private lateinit var personService: PersonService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName("Http PUT method로 성공 테스트")
    @Test
    fun `통합 test put persons `() {
        //given
        val expectedResultDto = OBJECT_MAPPER.writeValueAsString(ResultDto(listOf(0, 1), listOf(1, 2)))

        every {
            personService.registerOrSavePersons(
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
        } returns ResultDto(listOf(0, 1), listOf(1, 2))

        //when
        mockMvc.put("$BASE_URL/persons") {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = """
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
                        } , {
                          "person_id" : 2,
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
        }
            .andDo { println() }
                //then
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(expectedResultDto)
                }
            }
    }

    companion object {
        const val BASE_URL = "/api/v1/simple-crud"
    }
}