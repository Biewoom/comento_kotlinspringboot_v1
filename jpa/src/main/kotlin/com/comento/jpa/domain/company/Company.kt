package com.comento.jpa.domain.company

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "company")
data class Company(

    @Column(name = "founding_date")
    val foundingDate: YMD,

    @Column(name = "name")
    val name: String,

    @Column(name = "country")
    val country: String
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null

    val id: Long
        get() = _id ?: 0
}




