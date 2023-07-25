package com.umerscode.employeemanager.Service;

import com.umerscode.employeemanager.Entity.Users;
import com.umerscode.employeemanager.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
   @InjectMocks
    private UserService underTest;



    @Test
    void itShouldCreateNewUser(){
        //given
        Users user1 = new Users("umer","pass","ADMIN",true,true,true,true);
        when(userRepository.save(user1)).thenReturn(user1);

        //when
        Users expectedUser = underTest.registerNewUser(user1);

        //then

        assertThat(expectedUser).isNotNull();
        assertThat(expectedUser).isEqualTo(user1);

        ArgumentCaptor<Users> argumentCaptor = ArgumentCaptor.forClass(Users.class);
        verify(userRepository).save(argumentCaptor.capture());

        Users captorValue = argumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(user1);
    }

    @Test
    void itShouldThrowIfUserDoesNotHaveUsername(){
    //given
        Users user1 = new Users("","pass","ADMIN",true,true,true,true);
        Users user2 = new Users(null,"pass","ADMIN",true,true,true,true);

        //when & then
        assertThatThrownBy(()->underTest.registerNewUser(user2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Username or password is required");
    }

    @Test
    void itShouldThrowIfUserDoesNotHavePassword(){
        //given
        Users user1 = new Users("umer","","ADMIN",true,true,true,true);
        Users user2 = new Users("umer",null,"ADMIN",true,true,true,true);

        //when & then
        assertThatThrownBy(()->underTest.registerNewUser(user2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Username or password is required");
    }
}