package org.karensystem.core.annotation.advSearch;

import lombok.RequiredArgsConstructor;
import org.karensystem.core.date.DateMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class GenericSearchMapper {

    public GenericSearchDto toModel(GenericSearchDto itmDto) {

        DateMapper dateMapper=new DateMapper();

        for (SearchCriteria sc : itmDto.getSearchCriteriaList()) {
            //convert date
            if (sc.getValues() != null && sc.getValues().size()>0) {
                List<Object> values = sc.getValues();
                values.forEach((v) -> {
                    if (v.toString().indexOf("/") == 4 && v.toString().indexOf("/", 5) == 7)
                        v = dateMapper.toLocalDate(v.toString());
                });
                continue;
            }
            if (sc.getValue() != null) {
                Object v = sc.getValue();
                if (v.toString().indexOf("/") == 4 && v.toString().indexOf("/", 5) == 7) {
                    v = dateMapper.toLocalDate(v.toString());
                    sc.setValue((LocalDate)v);
                }

            }
        }

        return itmDto;
    }
}





