package com.pomanytskyi.payroll.dao;

import com.pomanytskyi.payroll.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
