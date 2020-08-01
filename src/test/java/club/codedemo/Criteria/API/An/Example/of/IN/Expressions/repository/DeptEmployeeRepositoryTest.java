package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.repository;

import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.DeptEmployee;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeptEmployeeRepositoryTest {
    @Autowired
    DeptEmployeeRepository deptEmployeeRepository;

    @Autowired
    DeptEmployeeCrudRepository deptEmployeeCrudRepository;

    @Test
    void titleIn() {
        String title = RandomString.make(4);
        for (int i = 0; i < 10; i++) {
            DeptEmployee deptEmployee = new DeptEmployee();
            deptEmployee.setTitle(title);
            this.deptEmployeeCrudRepository.save(deptEmployee);
        }

        String title1 = RandomString.make(5);
        for (int i = 0; i < 10; i++) {
            DeptEmployee deptEmployee = new DeptEmployee();
            deptEmployee.setTitle(title1);
            this.deptEmployeeCrudRepository.save(deptEmployee);
        }

        String[] titles = {title};
        assertEquals(10, this.deptEmployeeRepository.titleIn(titles).size());

        String[] titles1 = {title, title1};
        assertEquals(20, this.deptEmployeeRepository.titleIn(titles1).size());
    }
}