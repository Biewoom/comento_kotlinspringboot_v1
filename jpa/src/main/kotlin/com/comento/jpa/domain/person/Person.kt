package com.comento.jpa.domain.person

import com.comento.jpa.domain.account.Account
import com.comento.jpa.domain.common.enums.Gender
import com.comento.jpa.domain.company.Company
import com.comento.jpa.domain.country.Country
import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", precision = 255)
    private var _id: Long? = null
){
    val id: Long
        get() = _id ?: throw RuntimeException()

    @Column(name = "name", length = 30)
    lateinit var name: String

    @Column(name = "gender")
    @Enumerated(value = EnumType.ORDINAL)
    lateinit var gender: Gender

    @Column(name = "age", precision = 10)
    var age: Int? = null

    @Column(name = "height", precision = 10)
    var height: Int? = null

    @Column(name = "weight", precision = 10)
    var weight: Int? = null

    @Column(name = "is_married")
    var isMarried: Boolean? = null

    // FK
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country", referencedColumnName = "name")
    lateinit var country: Country

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company")
    var company: Company? = null

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "owner")
    lateinit var account: Account

    override fun toString(): String {
        return "Person(name='$name', gender=$gender, country='$country', age=$age)"
    }

    fun updateId(_id: Number){
        this._id = _id.toLong()
    }

}


