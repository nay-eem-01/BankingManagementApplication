package org.example.firstproject.specification;

import org.example.firstproject.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class UserSpecification {

    public static  Specification<User> isNameStartsWith(String prefix){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"),prefix+"%"));
    }
    public static  Specification<User> hasName(String name){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"),name));
    }
    public static Specification<User> hasEmail(){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"),"%@gmail.com"));
    }

    public static Specification<User> userForLongTime(LocalDateTime cutOffDate){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("createdDate"),cutOffDate));
    }


}
