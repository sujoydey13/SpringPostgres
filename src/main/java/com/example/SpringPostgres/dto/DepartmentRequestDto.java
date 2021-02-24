package com.example.SpringPostgres.dto;

import lombok.Data;

@Data
public class DepartmentRequestDto {
    private Long id;
    private String name;
    private String departmentCode;
}
