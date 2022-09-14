package com.comento.jpa.domain.personItem

import com.comento.jpa.domain.item.Item
import com.comento.jpa.domain.person.Person
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "person_item")
data class PersonItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var _id: Long? = null
) {
    val id: Long
        get() = _id ?: throw IllegalStateException()

    @Column(name = "price", nullable = false)
    var price: Double = 0.0

    @Column(name = "count", nullable = false)
    var count: Long = 0L

    // FK
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    lateinit var person: Person

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    lateinit var item: Item

}