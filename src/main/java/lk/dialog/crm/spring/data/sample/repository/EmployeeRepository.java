package lk.dialog.crm.spring.data.sample.repository;

import lk.dialog.crm.spring.data.sample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
