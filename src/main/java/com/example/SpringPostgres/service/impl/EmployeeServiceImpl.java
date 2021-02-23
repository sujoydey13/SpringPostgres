package com.example.SpringPostgres.service.impl;

import com.example.SpringPostgres.dto.EmployeeRequestDto;
import com.example.SpringPostgres.dto.EmployeeResponseDto;
import com.example.SpringPostgres.entity.Employee;
import com.example.SpringPostgres.repository.EmployeeRepository;
import com.example.SpringPostgres.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto,employee);

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
            employeeFromDb.setName(employeeFromDb.getName());
            employeeFromDb.setDepartmentName(employeeRequestDto.getDepartmentName());

            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee,responseDto);
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

}
