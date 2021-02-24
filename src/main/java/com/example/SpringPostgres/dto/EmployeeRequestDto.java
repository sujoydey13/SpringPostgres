package com.example.SpringPostgres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
    private long id;
    private String name;
    private DepartmentRequestDto departmentRequestDto;
}
