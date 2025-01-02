package org.karensystem.core.annotation.advSearch;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public GenericSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final GenericSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final GenericSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public List<SearchCriteria> getParam(){return params;}
    public Specification<T> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<T> result = new GenericSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new GenericSpecification(criteria))
                    : Specification.where(result).or(new GenericSpecification(criteria));
        }

        return result;
    }
}
