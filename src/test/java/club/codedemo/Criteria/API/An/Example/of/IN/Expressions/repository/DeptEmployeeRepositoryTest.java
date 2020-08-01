package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.repository;

import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.Department;
import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.DeptEmployee;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeptEmployeeRepositoryTest {
    private String title = RandomString.make(4);
    private String title1 = RandomString.make(4);
    private String departmentName = RandomString.make(4);

    @Autowired
    DepartmentCrudRepository departmentCrudRepository;

    @Autowired
    DeptEmployeeRepository deptEmployeeRepository;

    @Autowired
    DeptEmployeeCrudRepository deptEmployeeCrudRepository;

    @BeforeAll
    public void beforeAll() {
        Department department = new Department();
        department.setName(departmentName);
        this.departmentCrudRepository.save(department);

        Department department1 = new Department();
        department.setName(RandomString.make(2));
        this.departmentCrudRepository.save(department1);

        for (int i = 0; i < 20; i++) {
            DeptEmployee deptEmployee = new DeptEmployee();
            if (i % 2 == 0) {
                deptEmployee.setTitle(title);
                deptEmployee.setDepartment(department);
            } else {
                deptEmployee.setTitle(title1);
                deptEmployee.setDepartment(department1);
            }
            this.deptEmployeeCrudRepository.save(deptEmployee);
        }
    }

    @Test
    void titleIn() {
        String[] titles = {title};
        assertEquals(10, this.deptEmployeeRepository.titleIn(titles).size());

        String[] titles1 = {title, title1};
        assertEquals(20, this.deptEmployeeRepository.titleIn(titles1).size());
    }

    @Test
    public void titleExpressionIn() {
        String[] titles = {title};
        assertEquals(10, this.deptEmployeeRepository.titleExpressionIn(titles).size());

        String[] titles1 = {title, title1};
        assertEquals(20, this.deptEmployeeRepository.titleExpressionIn(titles1).size());
    }

    @Test
    public void subQuery() {
        assertEquals(10, this.deptEmployeeRepository.subQuery(departmentName).size());
    }
}