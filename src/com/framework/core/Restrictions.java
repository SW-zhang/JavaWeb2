package com.framework.core;

import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 条件构造器
 * 用于创建条件表达式
 *
 * @Class Name Restrictions
 */
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression neq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression like(String fieldName, String value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, MatchMode.ANYWHERE.toMatchString(value), Operator.LIKE);
    }

    /**
     * @param fieldName
     * @param value
     * @param matchMode
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression like(String fieldName, String value,
                                        MatchMode matchMode, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, matchMode.toMatchString(value), Operator.LIKE);
    }

    /**
     * 大于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.LT);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.LTE);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    public static SimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull && StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.GTE);
    }

    /**
     * 值为null
     *
     * @param fieldName
     * @return
     * @Methods Name isNull
     */
    public static SimpleExpression isNull(String fieldName) {
        return new SimpleExpression(fieldName, null, Operator.ISNULL);
    }

    /**
     * 值不为Null
     *
     * @param fieldName
     * @return
     * @Methods Name isNotNull
     */
    public static SimpleExpression isNotNull(String fieldName) {
        return new SimpleExpression(fieldName, null, Operator.ISNOTNULL);
    }

    /**
     * 并且
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression and(Criterion... criterions) {
        return LogicalExpression.getInstance(criterions, Operator.AND);
    }

    /**
     * 或者
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression or(Criterion... criterions) {
        return LogicalExpression.getInstance(criterions, Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName
     * @param value
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        return returnLogicalExpression(fieldName, value, Operator.EQ);
    }

    /**
     * 不包含
     *
     * @param fieldName
     * @param value
     * @param ignoreNull 如果是null或空字符串忽略此参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression nin(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        return returnLogicalExpression(fieldName, value, Operator.NE);
    }

    private static LogicalExpression returnLogicalExpression(String fieldName, Collection value, Operator operator) {
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, operator);
            i++;
        }
        if (i == 0) {    //无参情况
            ses = new SimpleExpression[2];
            ses[0] = new SimpleExpression(fieldName, null, Operator.ISNULL);
            ses[1] = new SimpleExpression(fieldName, null, Operator.ISNOTNULL);
            return LogicalExpression.getInstance(ses, Operator.AND);
        } else {
            return LogicalExpression.getInstance(ses, Operator.OR);
        }
    }
}
