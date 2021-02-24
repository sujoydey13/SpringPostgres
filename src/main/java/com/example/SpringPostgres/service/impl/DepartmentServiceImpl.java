package com.example.SpringPostgres.service.impl;

import com.example.SpringPostgres.dto.DepartmentRequestDto;
import com.example.SpringPostgres.dto.DepartmentResponseDto;
import com.example.SpringPostgres.entity.Department;
import com.example.SpringPostgres.entity.Employee;
import com.example.SpringPostgres.repository.DepartmentRepository;
import com.example.SpringPostgres.repository.EmployeeRepository;
import com.example.SpringPostgres.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentRequestDto,department);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment,departmentResponseDto);
        return departmentResponseDto;
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id){
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isPresent()){
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
            BeanUtils.copyProperties(departmentOptional.get(),departmentResponseDto);
            return departmentResponseDto;
        }
        return null;
    }

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartmentById(DepartmentRequestDto departmentRequestDto, Long id){
        Department department = departmentRepository.findById(id).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(id);

        department.setName(departmentRequestDto.getName());
        Department savedDepartment = departmentRepository.save(department);

        /*if(id !=null){
            throw new RuntimeException("By Error");
        }*/

        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDto.getDepartmentCode());
                });
        employeeRepository.saveAll(employeeList);

        return null;
    }

    @Override
    public DepartmentResponseDto deleteDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isPresent()){
            DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
            BeanUtils.copyProperties(departmentOptional.get(),departmentResponseDto );
            departmentRepository.deleteById(id);
            return departmentResponseDto ;
        }
        return null;
    }

    public Department getDepartmentBy_Id(Long id){
        return departmentRepository.findById(id).get();
    }
}
