package com.comento.jpa.service

import com.comento.jpa.domain.repository.PersonRepository
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
            .forEachIndexed { i, person ->
                val id = personRepository.save(person).id

                if (existedPersonIdList.contains(id)) resultTypes.add(0)
                else resultTypes.add(1)

                personIds.add(id)
            }

        return GeneratePersonsResponseDto(resultTypes, personIds)
    }
}