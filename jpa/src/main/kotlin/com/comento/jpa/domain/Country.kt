package com.comento.jpa.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Country(

    @Id @Column(name = "zip_code")
    val zipCode: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "capital_city")
    val capitalCity: String

)