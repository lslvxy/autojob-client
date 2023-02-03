package com.laysan.autojob.client.repository;

import com.laysan.autojob.client.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(Long userId);

    List<Account> findByTime(String time);

    List<Account> findByUserIdAndType(String userId, String type);

    Account findByUserIdAndTypeAndAccount(String userId, String type, String account);
}
