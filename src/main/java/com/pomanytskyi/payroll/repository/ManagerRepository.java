package com.pomanytskyi.payroll.repository;

import com.pomanytskyi.payroll.domain.Manager;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ManagerRepository extends Repository<Manager, Long> {

    Manager save(Manager manager);

    Manager findByName(String name);

}
