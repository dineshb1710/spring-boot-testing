package com.dineshb.springboot.testing.service.impl;

import com.dineshb.springboot.testing.exception.EmployeeNotFoundException;
import com.dineshb.springboot.testing.exception.NonUniqueEmployeeException;
import com.dineshb.springboot.testing.model.Employee;
import com.dineshb.springboot.testing.repository.EmployeeRepository;
import com.dineshb.springboot.testing.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new NonUniqueEmployeeException("Employee already exist(s) with email : " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("No Employee exist with id : " + id);
        }
        return employee.get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
