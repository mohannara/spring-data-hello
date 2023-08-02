package lk.dialog.crm.spring.data.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.dialog.crm.spring.data.sample.exception.ResourceNotFoundException;
import lk.dialog.crm.spring.data.sample.model.Employee;
import lk.dialog.crm.spring.data.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

      //get a employee from db id
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                  "Employee not found for this id :: " + employeeId));

    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                  "Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }



    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                                              .orElseThrow(() -> new ResourceNotFoundException(
                                                  "Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
