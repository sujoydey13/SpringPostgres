package com.example.SpringPostgres.repository;

import com.example.SpringPostgres.entity.Department;
import com.example.SpringPostgres.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);
    List<Employee> findByDepartment_Id(Long id);

    @Query("Select e From Employee e where e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    @Query(value="SELECT * FROM employee e WHERE e.department_id = ?1",nativeQuery = true)
    List<Employee> getEmployeeListByNAtiveQuery(Long departmentId);
}
