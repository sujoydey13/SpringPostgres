package com.example.SpringPostgres.repository;

import com.example.SpringPostgres.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
