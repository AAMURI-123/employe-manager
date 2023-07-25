package com.umerscode.employeemanager.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umerscode.employeemanager.Entity.Employee;
import com.umerscode.employeemanager.Service.EmployeeServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeController.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeServiceImp employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee1;

    @BeforeEach
    void setUp(){
    employee1 = new Employee("Alex",
                "alex@gmail.com","Full Stack","6542587895",
                "http://localimage/avatar-2.png");
    }

    @Test
    void itShouldGetAllEmployees() throws Exception {

        Employee employee2 = new Employee("Mariam",
                "mariam@gmail.com","Front-end","6542587789",
                "http://localimage/avatar-3.png");

        List<Employee> list = Arrays.asList(employee1,employee2);

        when(employeeService.getAllEmployee()).thenReturn(list);

        //Act
        mockMvc.perform(get("/employee/getAll"))
                .andExpect(status().isOk());

    }

    @Test
    void itShouldCreateEmployee() throws Exception{
        //Arrange

        when(employeeService.addEmployee(employee1)).thenReturn(employee1);

        mockMvc.perform(post("/employee/add")
               .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
               .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(employee1.getName()))
                .andExpect(jsonPath("$.email").value(employee1.getEmail()));

        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService).addEmployee(argumentCaptor.capture());
        Employee captorValue = argumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(employee1);


    }


    @Test
    void itShouldGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(1l)).thenReturn(of(employee1));

        //act
        //assert
            mockMvc.perform(get("/employee/find/{id}",1l))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(employee1.getName()))
                    .andExpect(jsonPath("$.email").value(employee1.getEmail()));

       ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeService).getEmployeeById(argumentCaptor.capture());
        Long captorValue = argumentCaptor.getValue();
        assertThat(1l).isEqualTo(captorValue);

    }

    @Test
    void itShouldUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(employee1)).thenReturn(employee1);
        //Act, Assert
        mockMvc.perform(put("/employee/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(employee1.getName()))
                .andExpect(jsonPath("$.email").value(employee1.getEmail()));

        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeService).updateEmployee(argumentCaptor.capture());
        Employee captorValue = argumentCaptor.getValue();
        assertThat(employee1).isEqualTo(captorValue);
           }

        @Test
    void itShouldDeleteEmployeeById() throws Exception {

        doNothing().when(employeeService).deleteEmployeeById(1l);

     mockMvc.perform(delete("/employee/{id}",1l))
            .andExpect(status().isOk());
     verify(employeeService).deleteEmployeeById(any(Long.class));

        }
}