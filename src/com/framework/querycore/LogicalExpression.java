package com.framework.querycore;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑条件表达式 用于复杂条件时使用，如多属性多对应值的OR查询等
 */
public class LogicalExpression implements Criterion {
    private Criterion[] criterion;    // 逻辑表达式中包含的表达式
    private Operator operator;        //计算符

    public static LogicalExpression getInstance(Criterion[] criterions, Operator operator) {
        List<Criterion> list = new ArrayList<Criterion>();
        for (int i = 0; i < criterions.length; i++) {
            if (criterions[i] != null) {
                list.add(criterions[i]);
            }
        }
        if (list.isEmpty()) {
            return null;
        } else {
            return new LogicalExpression(list.toArray(new Criterion[list.size()]), operator);
        }
    }

    private LogicalExpression(Criterion[] criterions, Operator operator) {
        this.criterion = criterions;
        this.operator = operator;
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (int i = 0; i < this.criterion.length; i++) {
            if (this.criterion[i] != null) {
                predicates.add(this.criterion[i].toPredicate(root, query, builder));
            }
        }
        if (predicates.isEmpty()) {
            return null;
        }
        switch (operator) {
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            case AND:
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            default:
                return null;
        }
    }

}