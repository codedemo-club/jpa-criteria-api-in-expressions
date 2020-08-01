package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.repository;

import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentCrudRepository extends CrudRepository<Department, Long> {
}
