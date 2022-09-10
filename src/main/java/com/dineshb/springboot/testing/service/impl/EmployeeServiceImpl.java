package com.dineshb.springboot.testing.service.impl;

import com.dineshb.springboot.testing.exception.NonUniqueEmployeeException;
import com.dineshb.springboot.testing.model.Employee;
import com.dineshb.springboot.testing.repository.EmployeeRepository;
import com.dineshb.springboot.testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new NonUniqueEmployeeException("Employee already exist(s) with email : " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
