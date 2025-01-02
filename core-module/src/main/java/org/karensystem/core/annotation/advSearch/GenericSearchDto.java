package org.karensystem.core.annotation.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}
