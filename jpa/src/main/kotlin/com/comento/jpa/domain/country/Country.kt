package com.comento.jpa.domain.country

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "country")
data class Country (
    @Id @Column(name = "zip_code")
    val zipCode: Long,
){

    @Column(name = "name")
    lateinit var name: String

    @Column(name = "capital_city")
    lateinit var capitalCity: String

}
