package club.codedemo.Criteria.API.An.Example.of.IN.Expressions.repository;

import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.Department;
import club.codedemo.Criteria.API.An.Example.of.IN.Expressions.entity.DeptEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

@Repository
public class DeptEmployeeRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * CriteriaBuilder.In 查询示例
     *
     * @param titles 标题数组
     * @return
     */
    public List<DeptEmployee> titleIn(String[] titles) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptEmployee> criteriaQuery =
                criteriaBuilder.createQuery(DeptEmployee.class);
        Root<DeptEmployee> root = criteriaQuery.from(DeptEmployee.class);
        CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("title"));
        for (String title : titles) {
            inClause.value(title);
        }
        CriteriaQuery<DeptEmployee> inQuery = criteriaQuery.select(root).where(inClause);
        TypedQuery<DeptEmployee> query = this.entityManager.createQuery(inQuery);
        return query.getResultList();
    }

    /**
     * ExpressionIn 查询示例
     *
     * @param titles 标题数组
     * @return
     */
    public List<DeptEmployee> titleExpressionIn(String[] titles) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptEmployee> criteriaQuery =
                criteriaBuilder.createQuery(DeptEmployee.class);
        Root<DeptEmployee> root = criteriaQuery.from(DeptEmployee.class);
        CriteriaQuery<DeptEmployee> inQuery = criteriaQuery.select(root)
                                                           .where(root.get("title")
                                                                      .in(titles));
        TypedQuery<DeptEmployee> query = this.entityManager.createQuery(inQuery);
        return query.getResultList();
    }

    /**
     * 子查询
     * @param departmentName 部门名称
     * @return
     */
    public List<DeptEmployee> subQuery(String departmentName) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<DeptEmployee> criteriaQuery =
                criteriaBuilder.createQuery(DeptEmployee.class);
        Root<DeptEmployee> employeeRoot = criteriaQuery.from(DeptEmployee.class);

        Subquery<Department> subquery = criteriaQuery.subquery(Department.class);
        Root<Department> dept = subquery.from(Department.class);
        subquery.select(dept)
                .distinct(true)
                .where(criteriaBuilder.like(dept.get("name"), "%" + departmentName + "%"));

        CriteriaQuery<DeptEmployee> subQuery1 = criteriaQuery.select(employeeRoot)
                                                            .where(criteriaBuilder.in(employeeRoot.get("department")).value(subquery));

        TypedQuery<DeptEmployee> deptEmployeeTypedQuery = this.entityManager.createQuery(subQuery1);
        return deptEmployeeTypedQuery.getResultList();
    }
}
