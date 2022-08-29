package com.comento.dbless.presentation.dto

data class Persons(
    // Persons라는 data class를 통해 기존 JAVA DTO를 수행
    // 함수 부분
    val persons: List<Person>,
    val sortBy: String,
    val sortOrder: String
)

data class Person(
    // Persons라는 data class를 통해 기존 JAVA DTO를 수행
    // 변수 부분
    val age: Int,
    val height: Int,
    val id: String,
    val name: String
)