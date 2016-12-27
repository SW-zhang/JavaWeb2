package com.framework.core;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 条件接口
 * 用户提供条件表达式接口
 *
 * @Class Name Criterion
 */
public interface Criterion {
    /**
     * 组装基于JPA API的动态查询对象
     *
     * @param root
     * @param query
     * @param builder
     * @return
     * @Methods Name toPredicate
     */
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder);
}
