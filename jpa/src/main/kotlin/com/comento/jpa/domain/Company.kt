package com.comento.jpa.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Company(

    @Column(name = "founding_date")
    val foundingDate: YMD,

    @Column(name = "name")
    val name: String,

    @Column(name = "country")
    val country: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null

    val id: Long
        get() = _id ?: 0
}

@JvmInline
value class YMD(private val date: String) {

    init {
        val re = Regex("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
        if (!re.matches(date)) throw IllegalArgumentException("Incorrect Format")
    }
}