package me.whiteship.springbootjpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest  //Slicing Test
//@SpringBootTest // All Test
public class AccountRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getDriverName());
            System.out.println(connection.getMetaData().getUserName());
            System.out.println(connection.getMetaData().getURL());
        }
    }
    @Test
    public void test(){
        Account account = new Account();
        account.setUsername("seungjun");
        account.setPassword("1234");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull();
        System.out.println(newAccount.toString());
        System.out.println("=====================================================================");

        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        System.out.println(existingAccount.toString());
        assertThat(existingAccount).isNotEmpty();

        Optional<Account> nonExistingAccount = accountRepository.findByUsername("Lee");
        assertThat(nonExistingAccount).isEmpty();
    } 
}