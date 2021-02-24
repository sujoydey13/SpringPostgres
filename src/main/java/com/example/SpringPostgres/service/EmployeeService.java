package com.example.SpringPostgres.service;

import com.example.SpringPostgres.dto.EmployeeRequestDto;
import com.example.SpringPostgres.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    public EmployeeResponseDto getEmployeeById(Long id);

    public EmployeeResponseDto updateEmployeeById(EmployeeRequestDto employeeRequestDto, Long id);

    public EmployeeResponseDto deleteEmployeeById(Long id);

    public List<EmployeeResponseDto> getEmployeeByDepartmentId(Long departmentId);
}
