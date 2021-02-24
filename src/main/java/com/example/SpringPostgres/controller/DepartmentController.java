package com.example.SpringPostgres.controller;

import com.example.SpringPostgres.dto.DepartmentRequestDto;
import com.example.SpringPostgres.dto.DepartmentResponseDto;
import com.example.SpringPostgres.dto.EmployeeRequestDto;
import com.example.SpringPostgres.dto.EmployeeResponseDto;
import com.example.SpringPostgres.entity.Department;
import com.example.SpringPostgres.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto){
        return departmentService.createDepartment(departmentRequestDto);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDto getDepartmentById(@PathVariable("id") Long id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDto updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentRequestDto departmentRequestDto) {
        return departmentService.updateDepartmentById(departmentRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public DepartmentResponseDto deleteDepartment(@PathVariable("id") Long id){
        return departmentService.deleteDepartmentById(id);
    }

    /*@GetMapping("/{id}")
    public Department getDepartmentBy_Id(@PathVariable("id") Long id){
        return departmentService.getDepartmentBy_Id(id);
    }*/

}
