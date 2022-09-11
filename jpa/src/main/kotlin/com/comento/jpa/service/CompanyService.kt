package com.comento.jpa.service

import com.comento.jpa.domain.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
}