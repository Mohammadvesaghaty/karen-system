package org.karensystem.core.annotation.advSearch;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

import java.lang.reflect.Field;
import java.util.List;

public class GenericQuery {



    public static <T> String getQuery(Object  clazz, GenericSearchDto genericSearch,String selectPart){
        return getQuery(clazz,genericSearch,selectPart,null);
    }
    public static <T> String getQuery(Object  clazz, GenericSearchDto genericSearch){
        return getQuery(clazz,genericSearch,null,null);
    }
    public static <T> String getQuery(Object  clazz, GenericSearchDto genericSearch,String selectPart,String orderBy){
        List<SearchCriteria> criteriaList = genericSearch.getSearchCriteriaList();

        StringBuilder  stringBuilder=new StringBuilder();

        stringBuilder.append(getSelectClause(clazz,selectPart));

        stringBuilder.append(" where 1 = 1 ");
        for(SearchCriteria sc: criteriaList) {
            for (Field field : clazz.getClass().getDeclaredFields()) {
                String filterKey=sc.getFilterKey();
                if (filterKey.toLowerCase().equals(field.getName().toLowerCase())) {
                    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                    if (joinColumn != null) {
                        stringBuilder
                                .append(" and ")
                                .append(toPredicate(field.getName()+".id", sc.getOperation(), sc.getValue().toString()));
                    } else {
                        Column column = field.getAnnotation(Column.class);
                        if (column != null) {
                            stringBuilder
                                    .append(" and ")
                                    .append(toPredicate(field.getName(), sc.getOperation(), sc.getValue().toString()));
                                    //.append(toPredicate(column.name(), sc.getOperation(), sc.getValue().toString()));
                        } else {
                            stringBuilder
                                    .append(" and ")
                                    .append(toPredicate(field.getName(), sc.getOperation(), sc.getValue().toString()));
                        }
                    }
                }else if(filterKey.contains(".") && filterKey.toLowerCase().startsWith(field.getName().toLowerCase())){
                    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                    if (joinColumn != null) {
                        stringBuilder
                                .append(" and ")
                                .append(toPredicate("u."+filterKey, sc.getOperation(), sc.getValue().toString()));
                    }
                }
            }
        }
        if(orderBy!=null)
            stringBuilder.append(" order by ").append(orderBy);

        return stringBuilder.toString();
    }
    private static  String getCriteriaPart(  String columnName, SearchOperations operation, String value) {
        return new StringBuilder()
                .append(columnName)
                .append(operation.getValue().replace("x",value))
                .toString();
    }
    private static  String getCriteriaPartBetween(  String columnName, SearchOperations operation, String value) {
        String x=value.split(",")[0];
        String y=value.split(",")[1];
        return new StringBuilder()
                .append(columnName)
                .append(operation.getValue().replace("x",x).replace("y",y))
                .toString();
    }
    private static  String toPredicate(String columnName,String operation,String value) {
        switch(operation.toUpperCase()) {
            case "IN":
                return getCriteriaPart(columnName, SearchOperations.IN, value);
            case "NOT_IN":
                return getCriteriaPart(columnName, SearchOperations.NOT_IN, value);
            case "LIKE":
                return getCriteriaPart(columnName, SearchOperations.LIKE, value);
            case "NOT LIKE":
                return getCriteriaPart(columnName, SearchOperations.NOT_LIKE, value);
            case "EQUAL":
                return getCriteriaPart(columnName, SearchOperations.EQUAL, value);
            case "BEGINS_WITH":
                return getCriteriaPart(columnName, SearchOperations.BEGINS_WITH, value);
            case "DOES_NOT_BEGIN_WITH":
                return getCriteriaPart(columnName, SearchOperations.DOES_NOT_BEGIN_WITH, value);
            case "ENDS_WITH":
                return getCriteriaPart(columnName, SearchOperations.ENDS_WITH, value);
            case "DOES_NOT_END_WITH":
                return getCriteriaPart(columnName, SearchOperations.DOES_NOT_END_WITH, value);
            case "GREATER_THAN":
                return getCriteriaPart(columnName, SearchOperations.GREATER_THAN, value);
            case "GREATER_THAN_EQUAL":
                return getCriteriaPart(columnName, SearchOperations.GREATER_THAN_EQUAL, value);
            case "LESS_THAN":
                return getCriteriaPart(columnName, SearchOperations.LESS_THAN, value);
            case "LESS_THAN_EQUAL":
                return getCriteriaPart(columnName, SearchOperations.LESS_THAN_EQUAL, value);
            case "BETWEEN":
                return getCriteriaPartBetween(columnName, SearchOperations.BETWEEN, value);
            case "NOT_BETWEEN":
                return getCriteriaPartBetween(columnName, SearchOperations.NOT_BETWEEN, value);
            case "NUL":
                return getCriteriaPart(columnName, SearchOperations.NUL, value);
            case "NOT_NULL":
                return getCriteriaPart(columnName, SearchOperations.NOT_NULL, value);
        }
        return null;
    }

    public static  String getSelectClause(Object clazz,String selectPart){
        String select="";
        if(selectPart!=null && !selectPart.isEmpty())
            select=String.format("select %s  from %s u ",selectPart,clazz.getClass().getName());
        else
            select=String.format("select u from %s u ",clazz.getClass().getName());

        return select;
    }
/*    public static  String getGroupByClause(StoVchHdr entity){
        for (Field field : entity.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                System.out.println(column.name());
            }
        }
        return null;
    }*/


}
