package com.umerscode.employeemanager.Service;

import com.umerscode.employeemanager.CustomException.UserNotFoundException;
import com.umerscode.employeemanager.Entity.Employee;
import com.umerscode.employeemanager.Repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeServices{

    private final EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getAllEmployee() {

        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
     if (!employeeRepo.existsById(id))
               throw new UserNotFoundException("Employee with id "+ id +" was not found");
    return  employeeRepo.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if (!employeeRepo.existsById(id))
            throw new UserNotFoundException("Employee with id "+ id +" was not found");
            employeeRepo.deleteById(id);
    }
}
