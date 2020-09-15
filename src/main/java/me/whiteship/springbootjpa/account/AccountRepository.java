package me.whiteship.springbootjpa.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // @Query(nativeQuery = true, value="select * from account where username='{0}'") //JPQL or nativeQuery = true? -> nativeQuery 사용가능
    // placeHolder 사용가능 아니면 JPQL 사용해야함
    Optional<Account> findByUsername(String username);
}
