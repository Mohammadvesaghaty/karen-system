package org.karensystem.core.annotation.advSearch;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class GenericSpecification<T> implements Specification<T> {

    private final SearchCriteria searchCriteria;

    public GenericSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        SearchOperation searchOperation = Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()));
        String strToSearch ="";
        if(!searchOperation.equals(SearchOperation.IN)) {
            strToSearch = searchCriteria.getValue().toString().toLowerCase();
        }
        Join<Object, Object> joinObject=null;
        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case IN:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.like(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                /*if(searchCriteria.getFilterKey().equals("AccVchType")) {
                    CriteriaBuilder.In<AccVchType> inClause = cb.in(root.get(searchCriteria.getFilterKey()));
                    searchCriteria.getValues().forEach((v) -> {
                        inClause.value((AccVchType) v);
                    });
                    return inClause;
                }*/

            case CONTAINS:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.like(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.notLike(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.like(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.notLike(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.like(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.notLike(cb.lower(joinObject.<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.equal(joinObject.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                joinObject = joinColumns(root,searchCriteria);
                if(joinObject!=null) {
                    return cb.notEqual(joinObject.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                              //  if(searchCriteria.getFilterKey().equals("deptName")){
              //      return cb.notEqual(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue() );
                // }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NUL:
                return cb.isNull(root.get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

            case GREATER_THAN:
                return cb.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
        }
        return null;
    }

    public Join< Object, Object> joinColumns(Root<T> root,SearchCriteria sc){
        return null;

    }
/*
    private Join<AccVchHdr, Department> departmentJoin(Root<AccVchHdr> root){
        return root.join("department");

    }
    */
}
