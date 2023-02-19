package com.springboot.springbootbackend.service.impl;

import com.springboot.springbootbackend.exception.ResourceNotFoundException;
import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// @Transactional is not required as Spring data JPA internally provides transaction to all it's method
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    // @Autowired is not required as Spring Bean only has 1 constructor, Springboot will automatically autowire the dependency
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(); // check
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }
//        throw new ResourceNotFoundException("Employee","Id",id);

        // using lamda expression
         return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // check if this employee doesn't exist in DB and throw exception
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee","Id",id));
        // employee exists so make changes
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save the changes
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // check whether employee doesn't exist in the DB and throw exception
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee","Id",id));
        // employee exists so delete
        employeeRepository.deleteById(id);
        return;
    }


}
