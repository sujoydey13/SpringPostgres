package com.example.SpringPostgres.service.impl;

import com.example.SpringPostgres.dto.DepartmentResponseDto;
import com.example.SpringPostgres.dto.EmployeeRequestDto;
import com.example.SpringPostgres.dto.EmployeeResponseDto;
import com.example.SpringPostgres.entity.Department;
import com.example.SpringPostgres.entity.Employee;
import com.example.SpringPostgres.repository.DepartmentRepository;
import com.example.SpringPostgres.repository.EmployeeRepository;
import com.example.SpringPostgres.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto,employee);

        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartmentRequestDto().getId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }
        else{
            Department department = new Department();
            department.setName(employeeRequestDto.getDepartmentRequestDto().getName());
            employee.setDepartment(department);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponseDto responseDto = new EmployeeResponseDto();

        BeanUtils.copyProperties(savedEmployee,responseDto);

        return responseDto;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(), responseDto);

            return responseDto;
        }
        return null;
    }

    @Override
    public EmployeeResponseDto updateEmployeeById(EmployeeRequestDto employeeRequestDto, Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDto.getName());

            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartmentRequestDto().getId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }

            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee,responseDto);
            responseDto.setDepartmentFromEntity(savedEmployee.getDepartment());
            return responseDto;
        }
        return null;
    }

    public EmployeeResponseDto deleteEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(),employeeResponseDto);
            employeeRepository.deleteById(id);
            return employeeResponseDto;
        }
        return null;
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeByDepartmentId(Long departmentId) {
        //Department department = departmentRepository.findById(departmentId).get();
        //List<Employee> employeeList = employeeRepository.findByDepartment(department);
        //List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);
        //List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(departmentId);
        List<Employee> employeeList = employeeRepository.getEmployeeListByNAtiveQuery(departmentId);
        List<EmployeeResponseDto> employeeResponseList = new ArrayList<>();
        for(Employee employee:employeeList)
        {
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee,responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseList.add(responseDto);
        }

        return employeeResponseList;
    }

}
