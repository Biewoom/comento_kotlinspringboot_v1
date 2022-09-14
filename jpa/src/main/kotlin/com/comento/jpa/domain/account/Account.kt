package com.comento.jpa.domain.account

import com.comento.jpa.domain.person.Person
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "account")
@IdClass(Account.AccountId::class)
data class Account(
    @Id
    @Column(name = "account_number")
    val accountNumber: String,
    @Id
    @Column(name = "bank")
    val bank: Short
) {
    @Column(name = "balance")
    var balance: Int = 0

    // FK
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    lateinit var owner: Person

    inner class AccountId: Serializable {
        var accountNumber: String? = null
        var bank: Short? = null
    }
}