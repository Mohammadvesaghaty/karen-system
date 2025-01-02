package org.karensystem.core.annotation.advSearch;

public enum SearchOperations {

    IN(" IN (x) "),
    NOT_IN(" NOT IN (x) "),
    LIKE(" LIKE %x% "),
    EQUAL(" = x "),
    NOT_EQUAL(" <> x "),
    NOT_LIKE(" NOT LIKE %x% "),
    BEGINS_WITH(" LIKE %x "),
    DOES_NOT_BEGIN_WITH(" NOT LIKE  %x "),
    ENDS_WITH(" LIKE x%"),
    DOES_NOT_END_WITH(" NOT LIKE x% "),
    GREATER_THAN(" > x "),
    GREATER_THAN_EQUAL(" >= x " ),
    LESS_THAN(" < x "),
    LESS_THAN_EQUAL(" <= x "),

    BETWEEN(" BETWEEN x AND y "),

    NOT_BETWEEN(" NOT BETWEEN  x AND y "),
    NUL(" IS NULL "),
    NOT_NULL(" IS NOT NULL ");

    private String value;

    SearchOperations(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
