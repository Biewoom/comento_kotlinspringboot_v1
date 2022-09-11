package com.comento.jpa.domain.repository

import com.comento.jpa.domain.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<Company, Long> {
}