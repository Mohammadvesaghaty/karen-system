package org.karensystem.core.annotation.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String filterKey;
    private Object value;
    private List<Object> values;
    private String operation;
    private String dataOption;

    public SearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }
}
