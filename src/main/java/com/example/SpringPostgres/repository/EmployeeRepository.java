package com.example.SpringPostgres.repository;

import com.example.SpringPostgres.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
