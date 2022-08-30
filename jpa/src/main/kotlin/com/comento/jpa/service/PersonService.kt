package com.comento.jpa.service

import com.comento.jpa.domain.BlindDateNotFoundException
import com.comento.jpa.domain.PersonNotFoundException
import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.domain.person.PersonRepository
import com.comento.jpa.presentation.BlindDateDto
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository
) {


    fun findBlindDateCoupleList(ageDiff: Int): List<Pair<BlindDateDto, BlindDateDto>> {

        val men = personRepository.findPeopleByGenderAndAgeNotNull(Gender.MALE).ifEmpty { throw PersonNotFoundException("men cannot be found") }
        val women = personRepository.findPeopleByGenderAndAgeNotNull(Gender.FEMALE).ifEmpty { throw PersonNotFoundException("women cannot be found") }

        val ll = mutableListOf<Pair<BlindDateDto, BlindDateDto>>()

        men.forEach { man ->
            women
                .filter { man.age!! - ageDiff <= it.age!! && it.age!! <= man.age!! }
                .forEach { woman ->
                    ll.add(Pair(BlindDateDto.fromPerson(man), BlindDateDto.fromPerson(woman)))
            }
            women
                .filter { man.age!! < it.age!! && it.age!! <= man.age!! + ageDiff  }
                .forEach{ woman ->
                    ll.add(Pair(BlindDateDto.fromPerson(woman), BlindDateDto.fromPerson(man)))
                }
        }

        return ll.ifEmpty { throw BlindDateNotFoundException("BlindDate Candidates with ageDiff ` $ageDiff ` cannot be Found") }
    }

}