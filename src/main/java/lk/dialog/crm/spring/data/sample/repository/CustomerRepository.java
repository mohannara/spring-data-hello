package lk.dialog.crm.spring.data.sample.repository;

import lk.dialog.crm.spring.data.sample.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
