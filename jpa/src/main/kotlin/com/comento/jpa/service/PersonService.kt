package com.comento.jpa.service

import com.comento.jpa.common.enum.Gender
import com.comento.jpa.domain.BlindDateNotFoundException
import com.comento.jpa.domain.repository.PersonRepository
import com.comento.jpa.presentation.dto.BlindDateDto
import com.comento.jpa.presentation.dto.GeneratePersonsResponseDto
import com.comento.jpa.presentation.dto.PersonDto
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun upsertPersons(personDtoList: List<PersonDto>): GeneratePersonsResponseDto {

        val existedPersonIdList = personDtoList
            .filter { personDto -> personDto.personId?.let { personRepository.existsById(it) } ?: false }
            .map { it.personId }
            .toList()

        val resultTypes = mutableListOf<Int>()
        val personIds = mutableListOf<Int>()

        val personList = personDtoList
            .map(PersonDto::toEntity)
            .forEach { person ->
                val id = personRepository.save(person).id

                if (existedPersonIdList.contains(id)) resultTypes.add(0)
                else resultTypes.add(1)

                personIds.add(id)
            }

        return GeneratePersonsResponseDto(resultTypes, personIds)
    }

    fun blindDate(ageDiff: Int): List<Pair<BlindDateDto, BlindDateDto>> {

        val maleList =
            personRepository.findByGenderAndAgeNotNull(Gender.MALE).ifEmpty { throw Exception("No male person data") }
        val femaleList = personRepository.findByGenderAndAgeNotNull(Gender.FEMALE)
            .ifEmpty { throw Exception("No female person data") }

        val coupleList = mutableListOf<Pair<BlindDateDto, BlindDateDto>>()

        maleList.forEach mReturn@{ male ->
            male.age ?: return@mReturn
            femaleList.forEach fReturn@{ female ->
                female.age ?: return@fReturn
                if (male.age!! - ageDiff <= female.age!! && male.age!! >= female.age!!) coupleList.add(
                    Pair(
                        male.toBlindDateDto(),
                        female.toBlindDateDto()
                    )
                )
                if (male.age!! < female.age!! && male.age!! + ageDiff >= female.age!!) coupleList.add(
                    Pair(
                        female.toBlindDateDto(),
                        male.toBlindDateDto()
                    )
                )
            }
        }

        return coupleList.ifEmpty { throw BlindDateNotFoundException("BlindDate Candidates with ageDiff ` $ageDiff ` cannot be Found")  }
    }
}
