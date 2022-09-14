package com.comento.jpa.service

import com.comento.jpa.domain.BlindDateNotFoundException
import com.comento.jpa.domain.PersonNotFoundException
import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.domain.country.CountryRepository
import com.comento.jpa.domain.person.Person
import com.comento.jpa.domain.person.PersonRepository
import com.comento.jpa.logger
import com.comento.jpa.presentation.BlindDateDto
import com.comento.jpa.presentation.PersonDto
import com.comento.jpa.presentation.ResultDto
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val countryRepository: CountryRepository
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

    @Transactional
    fun registerOrSavePersons(personRequests: List<PersonDto>): ResultDto {

        val resultTypes = (1..personRequests.size).map { 0 }.toMutableList()
        val personIds = (1..personRequests.size).map{ 2L }.toMutableList()

        val existedPersonIds = personRequests
            .filter { personDto -> personDto.personId?.let { personRepository.existsBy_id(it) } ?: false }
            .map { it.personId }
            .toSet()

        logger.info { "existedPersons: $existedPersonIds" }

        personRequests
            .map { personDto -> convertToPerson(personDto) }
            .forEachIndexed { index, person ->
                val id = personRepository.save(person).id

                if ( id in existedPersonIds ) resultTypes[index] = 0
                else resultTypes[index] = 1

                personIds[index] = id
            }

        return ResultDto(
                resultTypes = resultTypes,
                personIds = personIds
            )
    }

    private fun convertToPerson(personDto: PersonDto): Person {
        val ( personId: Long?, age: Int?, height: Int?, weight: Int?,
            name: String, gender: Gender?, isMarried: Boolean?, company: String?, country: String
        ) = personDto

        age?.let { if (it !in (0..100)) throw IllegalArgumentException("`age: $age` is not between 0 and 100") }
        height?.let { if (it !in (130..200) ) throw IllegalArgumentException("`height: $height` is not between 130 and 200") }
        weight?.let { if (it !in (30..200) ) throw IllegalArgumentException("`weight: $weight` is not between 30 and 200") }

        val person = Person().apply {
            this.name = name
            this.gender = gender ?: Gender.UNKNOWN
            this.country = countryRepository.findCountryByName(country) ?: throw RuntimeException()
            this.age = age
            this.height = height
            this.weight = weight
            this.isMarried = isMarried ?: false
            this.company = null
        }
        personId?.let { person.updateId(it) }

        return person
    }


}