package com.umerscode.employeemanager.Integration;

import com.umerscode.employeemanager.Entity.Employee;
import com.umerscode.employeemanager.Entity.Users;
import com.umerscode.employeemanager.Repository.EmployeeRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeManagerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeRepo employeeRepo;

    private String baseUrl = "http://localhost:";

    private Employee mariamResponse;
    private Employee alexResponse;
    private Employee alexObject;
    private Employee mariamObject;

    private static RestTemplate restTemplate;

    @BeforeAll
    static void beforeAll() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setUp() {
        baseUrl = baseUrl + port + "/employee";

          alexObject = new Employee("Alex",
                "alex23@gmail.com","Full Stack","6342587895",
                "http://localimage/avatar-2.png");
           mariamObject = new Employee("mariam",
                "mariam@gmail.com","Full Stack","60587895",
                "http://localimage/avatar-3.png");

         alexResponse = restTemplate.postForObject(baseUrl + "/add", alexObject, Employee.class);
         mariamResponse = restTemplate.postForObject(baseUrl + "/add", mariamObject, Employee.class);

    }

    @AfterEach
    void tearDown() {
        employeeRepo.deleteAll();
    }

    @Test
    void itShouldCreateNewEmployee(){

        Employee johnEmployee = new Employee("john",
                "john23@gmail.com","Full Stack","67587895",
                "http://localimage/avatar-2.png");
        //act
        Employee responseEmployee = restTemplate.postForObject(baseUrl + "/add",
                                                        johnEmployee, Employee.class);
    //assert
        assertThat(responseEmployee.getId()).isNotNull();
        assertThat(responseEmployee.getEmail()).isEqualTo(johnEmployee.getEmail());
    }

    @Test
    void itShouldGetAllEmployee(){
        //when
        List<Employee> responseList = restTemplate.getForObject(baseUrl + "/getAll", List.class);

        //then
        assertThat(responseList.size()).isEqualTo(2);

    }

    @Test
    void itShouldGetEmployeeById(){
        //when
        Employee responseEmployee = restTemplate.getForObject(baseUrl + "/find/" + alexResponse.getId(), Employee.class);

        //then
        assertThat(responseEmployee).isNotNull();
        assertThat(responseEmployee.getId() ).isNotNull();
        assertThat(responseEmployee.getEmail()).isEqualTo(alexObject.getEmail());
    }

    @Test
    void itShouldDeleteEmployeeById(){

        //when
        restTemplate.delete(baseUrl+"/"+ alexResponse.getId());
        //then
        int count = employeeRepo.findAll().size();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void itShouldUpdateEmployee(){

        alexResponse.setJobTitle("Front-end");

        //when
        restTemplate.put(baseUrl+"/update",alexResponse);

        //then
        Employee updateEmployee = restTemplate.getForObject(baseUrl + "/find/" + alexResponse.getId(), Employee.class);

        assertThat(updateEmployee.getJobTitle()).isEqualTo(alexResponse.getJobTitle());
    }


    @Test
    void itShouldRegisterNewUser(){
        //given
        Users user1 = new Users("aamuri","pass","ADMIN",true,true,true,true);

        //when
        Users newUser = restTemplate.postForObject("http://localhost:" + port + "/signup",
                user1, Users.class);
        //then
        assertThat(newUser).isNotNull();
        assertThat(newUser.getId()).isNotNull();
        assertThat(newUser.getUsername()).isEqualTo(user1.getUsername());
    }
}
