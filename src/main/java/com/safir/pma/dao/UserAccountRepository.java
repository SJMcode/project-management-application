package com.safir.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.safir.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

}
