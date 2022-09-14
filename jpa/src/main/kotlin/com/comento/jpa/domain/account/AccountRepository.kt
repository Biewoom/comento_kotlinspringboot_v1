package com.comento.jpa.domain.account


import org.springframework.data.repository.CrudRepository

interface AccountRepository: CrudRepository<Account, Account.AccountId> {

}