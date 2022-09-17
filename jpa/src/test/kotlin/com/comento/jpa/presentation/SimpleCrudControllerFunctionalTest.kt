package com.comento.jpa.presentation


import com.comento.jpa.OBJECT_MAPPER
import com.comento.jpa.toSingleStringWithoutSpace
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
internal class SimpleCrudControllerFunctionalTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName("Http PUT method로 E2E 성공 테스트")
    @Test
    fun `E2E test put persons `() {
        //given
        val expectedResultDto = OBJECT_MAPPER.writeValueAsString(ResultDto(listOf(0, 0), listOf(1, 2)))

        //when
        mockMvc.put("${SimpleCrudControllerWebMvcTest.BASE_URL}/persons") {
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