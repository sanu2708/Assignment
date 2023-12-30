package com.example.EmployWiseJavaAssignment.Repository;

import com.example.EmployWiseJavaAssignment.Models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
