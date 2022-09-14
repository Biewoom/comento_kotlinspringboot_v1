package com.comento.jpa.domain.company

import com.comento.jpa.domain.common.vo.YMD
import com.comento.jpa.domain.country.Country
import com.comento.jpa.domain.person.Person
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "company")
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: 0

    @Column(name = "founding_date")
    var foundingDate: YMD = YMD("2022-09-01")

    @Column(name = "name")
    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "name")
    lateinit var country: Country

    @OneToMany(mappedBy = "company")
    lateinit var employees: List<Person>
}




