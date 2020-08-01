package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @OneToMany(mappedBy="department")
    private List<DeptEmployee> employees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeptEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<DeptEmployee> employees) {
        this.employees = employees;
    }
}
