package com.springboot.springbootbackend.controller;

import com.springboot.springbootbackend.model.Employee;
import com.springboot.springbootbackend.service.impl.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// url can be added in brackets next to @PostMapping as well
@RequestMapping("/api/employees")
// @RestController is a convenient annotation that combines @Controller and @ResponseBody which eliminates the need to annotate every request handling method of the Controller class with the @ResponseBody annotation
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super(); // check
        this.employeeService = employeeService;
    }

    // create REST API

    // @PostMapping is used as this should handle post HTTP request
    // post request contain Employee JSON object
    @PostMapping
    // ResponseEntity is used as we can provide complete response details in this class e.g status header etc.
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        //@RequestBody annotation allows us to retrieve the request's body and automatically convert it to Java object
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
        // 1st parameter(body)-> employee object (line 17) 2nd parameter(HTTP status) -> created
    }

    // get all employee REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // get employee by Id REST API
    @GetMapping("{id}")
    // http://localhost:8080/api/employees/id
    // parameter in @PathVariable has to be the same as parameter in @GetMapping
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // update employee REST API
    // http://localhost:8080/api/employees/id
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
    }

    // delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
    }
}
