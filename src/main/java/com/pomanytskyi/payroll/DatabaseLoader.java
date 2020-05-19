package com.pomanytskyi.payroll;

import com.pomanytskyi.payroll.dao.EmployeeRepository;
import com.pomanytskyi.payroll.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.employeeRepository.save(new Employee("Anton", "Pomanytskyi", "Learn"));
        this.employeeRepository.save(new Employee("Example", "Example", "Description"));
        this.employeeRepository.save(new Employee("First", "First", "FirstDesc"));
        this.employeeRepository.save(new Employee("Second", "Second", "SecondDesc"));
        this.employeeRepository.save(new Employee("Third", "Third", "ThirdDesc"));
    }
}
