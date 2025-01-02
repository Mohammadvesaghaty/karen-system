package org.karensystem.core.annotation.advSearch;

public class PublicClassCriteria {


    public static <T> String getValueOf(GenericSearchDto genericSearch, String filterKey) {

        for (SearchCriteria sc : genericSearch.getSearchCriteriaList()) {
            if(filterKey.toLowerCase().equals(sc.getFilterKey().toLowerCase())){
                return sc.getValue().toString();
            }
        }
        return null;
    }
    public static <T> Integer getValueOfInt(GenericSearchDto genericSearch, String filterKey) {

        for (SearchCriteria sc : genericSearch.getSearchCriteriaList()) {
            if(filterKey.toLowerCase().equals(sc.getFilterKey().toLowerCase())){
                return Integer.valueOf(sc.getValue().toString());
            }
        }
        return null;
    }
    public static <T> Long getValueOfLong(GenericSearchDto genericSearch, String filterKey) {

        for (SearchCriteria sc : genericSearch.getSearchCriteriaList()) {
            if(filterKey.toLowerCase().equals(sc.getFilterKey().toLowerCase())){
                return Long.valueOf( sc.getValue().toString());
            }
        }
        return null;
    }
    public static <T> Object getValueOfObject(GenericSearchDto genericSearch, String filterKey) {

        for (SearchCriteria sc : genericSearch.getSearchCriteriaList()) {
            if(filterKey.toLowerCase().equals(sc.getFilterKey().toLowerCase())){
                return  sc.getValue();
            }
        }
        return null;
    }


}