package com.dineshb.springboot.testing.service;

import com.dineshb.springboot.testing.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    Employee findById(long id);

    Employee updateEmployee(Employee employee);

    void deleteById(long id);
}
