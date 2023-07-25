package com.umerscode.employeemanager.Service;

import com.umerscode.employeemanager.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServices {

    List<Employee> getAllEmployee();
    Optional<Employee> getEmployeeById(Long id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
}
