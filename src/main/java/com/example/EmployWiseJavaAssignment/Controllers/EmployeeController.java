package com.example.EmployWiseJavaAssignment.Controllers;

import com.example.EmployWiseJavaAssignment.Models.Employee;
import com.example.EmployWiseJavaAssignment.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.size() > 0)
        {
            return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No data Available",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
    {
        try{
            employeeRepository.save(employee);
            return  ResponseEntity.ok("Employee Added SuccessFully, Employee Id:-"+employee.getId());
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id)
    {
        Employee employee;
        try{
            employee = employeeRepository.findById(id).get();
            employeeRepository.deleteById(id);
            return  ResponseEntity.ok("SuccessFully deleted the data of EmployeeId:-" + employee.getId());
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Invalid Id!!",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id")String id, @RequestBody Employee employee)
    {
          Optional<Employee> employeeOptional= employeeRepository.findById(id);
          if(employeeOptional.isPresent())
          {
              Employee employeeSave = employeeOptional.get();
              employeeSave.setName(employee.getName() != null ? employee.getName() : employeeSave.getName());
              employeeSave.setEmailId(employee.getEmailId() !=null ? employee.getEmailId() : employeeSave.getEmailId());
              employeeSave.setPhoneNumber(employee.getPhoneNumber() !=null ? employee.getPhoneNumber() : employeeSave.getPhoneNumber());
              employeeSave.setReportsTo(employee.getReportsTo());
              employeeRepository.save(employeeSave);
              return ResponseEntity.ok("UpdateD data to Employee ID:" + id);
          }

      else
        {
            return new ResponseEntity<>("Invalid ID" +id , HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/manager/{employeeId}/{level}")
    public ResponseEntity<?> getNthLevelManager(
            @PathVariable String employeeId,
            @PathVariable int level) {
        try {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);

            if (employee == null) {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }

            String managerId = findNthLevelManager(employee, level);

            if (managerId == null) {
                return new ResponseEntity<>("Manager not found at specified level", HttpStatus.NOT_FOUND);
            }

            Employee manager = employeeRepository.findById(managerId).orElse(null);

            if (manager == null) {
                return new ResponseEntity<>("Manager not found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(manager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String findNthLevelManager(Employee employee, int level) {
        if (level <= 0) {
            return null;
        }

        String reportsTo = employee.getReportsTo();

        while (level > 1 && reportsTo != null) {
            Employee manager = employeeRepository.findById(reportsTo).orElse(null);
            if (manager == null) {
                return null;
            }
            reportsTo = manager.getReportsTo();
            level--;
        }

        return reportsTo;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeName") String sortBy) {
        try {
            Page<Employee> employees = employeeRepository.findAll(
                    PageRequest.of(page, size, Sort.by(sortBy))
            );

            return new ResponseEntity<>(employees.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
