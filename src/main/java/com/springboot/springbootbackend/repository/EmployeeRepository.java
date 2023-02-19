package com.springboot.springbootbackend.repository;

import com.springboot.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// two parameters required by JpaRepository are type of entity and type of primary key
// @Repository is not required above EmployeeRepository interface as JPARepository provides this internally inside @SimpleJPARepository
// by default spring data JPA made JpaRepository methods transactional so no need to add @Transactional annotation Service class
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
