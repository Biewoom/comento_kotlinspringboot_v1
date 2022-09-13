package com.comento.jpa.domain

import com.comento.jpa.common.enum.Gender
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private var _id: Int? = null
) {

    val id: Int
        get() = _id ?: 0

    @Column(name = "age")
    var age: Int? = null

    @Column(name = "height")
    var height: Int? = null

    @Column(name = "name")
    lateinit var name: String

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    lateinit var gender: Gender

    @Column(name = "weight")
    var weight: Int? = null

    @Column(name = "is_married")
    var isMarried: Boolean? = null

    @Column(name = "company")
    var company: String? = null

    @Column(name = "country")
    lateinit var country: String

    fun updateId(id: Int) {
        this._id = id
    }
}