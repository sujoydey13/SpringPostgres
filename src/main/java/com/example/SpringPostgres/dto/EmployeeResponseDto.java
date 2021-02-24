package com.example.SpringPostgres.dto;

import com.example.SpringPostgres.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private long id;
    private String name;
    private DepartmentResponseDto departmentResponseDto;

    public void setDepartmentFromEntity(Department departmentEntity){
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(departmentEntity.getId());
        departmentResponseDto.setName(departmentEntity.getName());
        this.departmentResponseDto = departmentResponseDto;
    }
}
