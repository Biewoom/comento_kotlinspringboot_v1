package com.comento.dbless.service

import com.comento.dbless.domain.Person
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PersonService {

    /**
     * person 사용자 지정 정렬 함수
     */
    fun sortPersonLogic(personList: List<Person>, sortBy: String, sortOrder: String): List<Person> {
        return when (sortOrder) {
            "des" -> {
                decSort(personList, sortBy)
            }

            "asc" -> {
                ascSort(personList, sortBy)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    /**
     * 오름차순 정렬 함수
     */
    fun ascSort(personList: List<Person>, sortBy: String): List<Person> {
        return when (sortBy) {
            "age" -> {
                personList.sortedBy { it.age }
            }

            "height" -> {
                personList.sortedBy { it.height }
            }

            "id" -> {
                personList.sortedBy { it.id }
            }

            "name" -> {
                personList.sortedBy { it.name }
            }

            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    /**
     * 내림차순 정렬 함수
     */
    fun decSort(personList: List<Person>, sortBy: String): List<Person> {
        return when (sortBy) {
            "age" -> {
                personList.sortedByDescending { it.age }
            }

            "height" -> {
                personList.sortedByDescending { it.height }
            }

            "id" -> {
                personList.sortedByDescending { it.id }
            }

            "name" -> {
                personList.sortedByDescending { it.name }
            }

            else -> {
                throw IllegalArgumentException()
            }
        }
    }
}