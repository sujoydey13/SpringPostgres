package com.example.SpringPostgres.service;

import com.example.SpringPostgres.dto.EmployeeRequestDto;
import com.example.SpringPostgres.dto.EmployeeResponseDto;

public interface EmployeeService {
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    public EmployeeResponseDto getEmployeeById(Long id);
    public EmployeeResponseDto updateEmployeeById(EmployeeRequestDto employeeRequestDto, Long id);
    public EmployeeResponseDto deleteEmployeeById(Long id);
}
