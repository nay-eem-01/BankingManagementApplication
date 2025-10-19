package org.example.bankingManagementApplication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.bankingManagementApplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> findByName(String name) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        criteriaQuery.select(userRoot)
                .where(criteriaBuilder.and
                (
                        criteriaBuilder.equal(userRoot.get("name"),name),
                        criteriaBuilder.like(userRoot.get("name"),"n%m"),
                        criteriaBuilder.like(userRoot.get("email"),"%@gmail.com%"),
                        criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.length(userRoot.get("name")),3))
                );

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);


        return typedQuery.getResultList();
    }
}
