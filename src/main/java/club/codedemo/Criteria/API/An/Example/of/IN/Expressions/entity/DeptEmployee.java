package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity;

import javax.persistence.*;

@Entity
public class DeptEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;

    @ManyToOne
    private Department department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}