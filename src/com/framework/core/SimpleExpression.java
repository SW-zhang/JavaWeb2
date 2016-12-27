package com.framework.core;

import javax.persistence.criteria.*;

/**
 * 简单条件表达式
 */
public class SimpleExpression implements Criterion {

    /**
     * entity对象属性名
     * 如果属性为嵌套对象，如createUser，可使用createUser.userName的方式查询其嵌套对象属性
     */
    private String fieldName;
    /**
     * 查询属性匹配值
     */
    private Object value;
    /**
     * 条件判断运算符
     */
    private Operator operator;

    public SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path expression = null;
        if (fieldName.contains(".")) {
            String[] names = fieldName.split("\\.");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldName);
        }

        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like((Expression<String>) expression, value + "");
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case ISNULL:
                return builder.isNull(expression);
            case ISNOTNULL:
                return builder.isNotNull(expression);
            default:
                return null;
        }
    }

}
