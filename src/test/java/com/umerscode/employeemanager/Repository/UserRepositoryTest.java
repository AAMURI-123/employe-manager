package com.umerscode.employeemanager.Repository;

import com.umerscode.employeemanager.Entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest(
        properties = {"spring.datasource.url = jdbc:h2//mem:db;DB_CLOSE_DELAY=-1",
                       "spring.datasource.username = sa",
                       "spring.datasource.password = sa",
                       "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect",
                       "spring.jpa.hibernate.ddl-auto = create-drop"}
)
class UserRepositoryTest {

   private UserRepository userRepository;

   @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void itShouldGetUserByUsername(){
       //given
       Users user1 = new Users("umer","pass","ADMIN",true,true,true,true);
       userRepository.save(user1);

       //when
       Optional<Users> expectedUser = userRepository.findByUsername(user1.getUsername());

       //then
       assertThat(expectedUser).isNotEmpty();
       assertThat(user1.getUsername()).isEqualTo(expectedUser.get().getUsername());
       assertThat(user1.getPassword()).isEqualTo(expectedUser.get().getPassword());
   }
}