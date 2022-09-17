package com.comento.jpa.domain.repository

import com.comento.jpa.domain.person.PersonRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class PersonRepositoryJpaTest {

    @Autowired
    lateinit var personRepository: PersonRepository

    @DisplayName("jpa existBy_id 통합 테스트")
    @Test
    fun `통합 test existBy_id`(){
        //given
        val _id1 = 1
        val _id2 = 10

        //when
        val res1 = personRepository.existsBy_id(_id1)
        val res2 = personRepository.existsBy_id(_id2)

        // then
        res1 shouldBe true
        res2 shouldBe false
    }
}