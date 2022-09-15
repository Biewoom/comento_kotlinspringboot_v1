package com.comento.jpa.service

import com.comento.jpa.domain.company.Company
import com.comento.jpa.domain.person.Gender
import com.comento.jpa.domain.person.Person
import com.comento.jpa.domain.person.PersonRepository
import com.comento.jpa.presentation.PersonRequest
import com.comento.jpa.presentation.PersonResult
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun insertPeople(requests: List<PersonRequest>): PersonResult {
        val resultTypes = (1..requests.size).map { 0 }.toMutableList()
        val personIds = (1..requests.size).map{ 2 }.toMutableList()

        val existedPersonIds = requests
            .filter { request -> request.personId?.let { personRepository.existsBy_id(it) } ?: false }
            .map { it.personId }
            .toSet()

        requests
            .map { request -> convertToPerson(request) }
            .forEachIndexed { index, person ->
                val id = personRepository.save(person).id

                if ( id in existedPersonIds ) resultTypes[index] = 0
                else resultTypes[index] = 1
                personIds[index] = id.toInt()
            }

        return PersonResult(
            resultTypes = resultTypes,
            personIds = personIds
        )
    }

    private fun convertToPerson(request: PersonRequest): Person {
        val ( personId: Int?, age: Int?, height: Int?, weight: Int?,
            name: String, gender: Gender?, isMarried: Boolean?, company: String?, country: String
        ) = request

        age?.let { if (it !in (0..100)) throw IllegalArgumentException("`age: $age` is not between 0 and 100") }
        height?.let { if (it !in (130..200) ) throw IllegalArgumentException("`height: $height` is not between 130 and 200") }
        weight?.let { if (it !in (30..200) ) throw IllegalArgumentException("`weight: $weight` is not between 30 and 200") }

        val person = Person(
            name = name,
            gender = gender ?: Gender.UNKNOWN,
            country = country
        )
        personId?.let { person.updateId(it) }
        person.age = age
        person.height = height
        person.weight = weight
        person.isMarried = isMarried ?: false
        person.company = company
        return person
    }

}