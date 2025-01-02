package org.karensystem.core.annotation.advSearch;


import org.karensystem.core.date.DateMapper;

public class GenericDateMapper {
    public static GenericSearchDto dateMapper(GenericSearchDto genericSearch){
        genericSearch.getSearchCriteriaList().forEach(searchCriteria -> {
            if(searchCriteria.getFilterKey().toLowerCase().equals("vchdate")){
                String[] vchDates=searchCriteria.getValue().toString().replace("'","").split(",");

                String format="'%s'";
                String res="";
                for(String date: vchDates) {
                    res=res+","+String.format(format, DateMapper.toLocalDate(date));
                }
                res=res.substring(1);
                searchCriteria.setValue(res);
            }
        });
        return genericSearch;
    }
}
