package com.comento.jpa.domain.person

import com.comento.jpa.domain.common.enums.Gender
import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(

    @Column(name = "name", length = 30)
    val name: String,

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    val gender: Gender,

    @Column(name = "country", length = 100)
    val country: String
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", precision = 255)
    private var _id: Int? = null

    val id: Int
        get() = _id ?: 0

    @Column(name = "age", precision = 10)
    var age: Int? = null

    @Column(name = "height", precision = 10)
    var height: Int? = null

    @Column(name = "weight", precision = 10)
    var weight: Int? = null

    @Column(name = "is_married")
    var isMarried: Boolean? = null

    @Column(name = "company", length = 100)
    var company: String? = null

    override fun toString(): String {
        return "Person(name='$name', gender=$gender, country='$country', age=$age)"
    }

    fun updateId(_id: Number){
        this._id = _id.toInt()
    }

}


