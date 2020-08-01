package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.repository;

import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.Department;
import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.DeptEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DeptEmployeeRepository {

    @Autowired
    EntityManager entityManager;

    public List<DeptEmployee> titleIn(String[] titles) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptEmployee> criteriaQuery =
                criteriaBuilder.createQuery(DeptEmployee.class);
        Root<DeptEmployee> root = criteriaQuery.from(DeptEmployee.class);
        CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("title"));
        for (String title : titles) {
            inClause.value(title);
        }
        CriteriaQuery<DeptEmployee> xxx = criteriaQuery.select(root).where(inClause);
        TypedQuery<DeptEmployee> query = this.entityManager.createQuery(xxx);
        return query.getResultList();
    }
}
