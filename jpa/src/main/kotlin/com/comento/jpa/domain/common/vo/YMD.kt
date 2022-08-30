package com.comento.jpa.domain.common.vo

@JvmInline
value class YMD(private val date: String) : Comparable<YMD> {

    init {
        val re = Regex("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
        if (!re.matches(date)) throw IllegalArgumentException("Incorrect Format")
    }

    override fun compareTo(other: YMD): Int {
        return date.compareTo(other.date)
    }

}