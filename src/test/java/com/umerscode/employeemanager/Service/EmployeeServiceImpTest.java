package com.umerscode.employeemanager.Service;

import com.umerscode.employeemanager.CustomException.UserNotFoundException;
import com.umerscode.employeemanager.Entity.Employee;
import com.umerscode.employeemanager.Repository.EmployeeRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImpTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImp underTest;

    @Test
    void canGetAllEmployee() {
        //when
        underTest.getAllEmployee();
        //then
        verify(employeeRepo).findAll();
    }

    @Test
    void itShouldGetEmployeeById() {
        //given
        long employeeId = 1l;
        given(employeeRepo.existsById(employeeId)).willReturn(true);
        //when
           underTest.getEmployeeById(employeeId);

        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeRepo).findById(argumentCaptor.capture());

        Long captorValue = argumentCaptor.getValue();

        assertEquals(captorValue,employeeId);
    }

    @Test
    void itShouldThrowIfEmployeeWithIdDoesNotExistToRetrieve(){
        //given
        long employeeId = 1l;

        //when
        //then
        assertThatThrownBy(()->underTest.getEmployeeById(employeeId))
                            .isInstanceOf(UserNotFoundException.class)
                            .hasMessage("Employee with id "+ employeeId +" was not found");
    }

    @Test
    void canAddEmployee() {

        //given
        Employee employee = new Employee("Alex",
                "alex@gmail.com","Full Stack","6542587895",
                "http://localimage/avatar-2.png");

        //when
        underTest.addEmployee(employee);
        //then
            ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
            verify(employeeRepo).save(argumentCaptor.capture());

                Employee captorValue = argumentCaptor.getValue();
                assertEquals(captorValue,employee);
    }

    @Test
    void canUpdateEmployee() {

        //given
        Employee employee = new Employee("Alex",
                "alex@gmail.com","Full Stack","6542587895",
                "http://localimage/avatar-2.png");

        //when
        underTest.updateEmployee(employee);

        //then
        verify(employeeRepo).save(employee);
    }

    @Test
    void itShouldDeleteEmployeeById() {

        //given
        long id = 1l;
        given(employeeRepo.existsById(id)).willReturn(true);
        //when
        underTest.deleteEmployeeById(id);

        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(employeeRepo).deleteById(argumentCaptor.capture());

        Long captorValue = argumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(id);


    }

    @Test
    void itShouldThrowWhenEmployeeWithIdDoesNotExistToDelete(){

        //given
        long id = 1l;

        //when
        //then
        assertThatThrownBy(()->underTest.deleteEmployeeById( id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Employee with id "+ id +" was not found");
    }

}