package com.example.SpringPostgres.service;


import com.example.SpringPostgres.dto.DepartmentRequestDto;
import com.example.SpringPostgres.dto.DepartmentResponseDto;
import com.example.SpringPostgres.entity.Department;

public interface DepartmentService {
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);
    public DepartmentResponseDto getDepartmentById(Long id);
    public DepartmentResponseDto updateDepartmentById(DepartmentRequestDto departmentRequestDto, Long id);
    public DepartmentResponseDto deleteDepartmentById(Long id);

    public Department getDepartmentBy_Id(Long id);
}
