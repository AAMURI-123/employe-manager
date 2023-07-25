package com.umerscode.employeemanager.Controller;

import com.umerscode.employeemanager.Entity.Employee;
import com.umerscode.employeemanager.Service.EmployeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImp employeeService;

  @GetMapping("/getAll")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
  public ResponseEntity<List<Employee>> getAllEmployee(){
      return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){

      Employee employee = employeeService.getEmployeeById(id).get();
      return new ResponseEntity<>(employee,HttpStatus.OK);

  }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
      return new ResponseEntity<>(employeeService.addEmployee(employee), CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
      return new ResponseEntity<>(employeeService.updateEmployee(employee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployeeById(@PathVariable("id") Long id)
    {
      employeeService.deleteEmployeeById(id);}
}


