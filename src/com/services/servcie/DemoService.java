package com.services.servcie;

import com.framework.core.Criteria;
import com.framework.core.Criterion;
import com.framework.core.PageParam;
import com.framework.core.Restrictions;
import com.framework.service.BaseService;
import com.framework.service.NativeEntityManager;
import com.services.dao.DemoDao;
import com.services.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DemoService extends BaseService {

    @Autowired
    private DemoDao demoDao;
    @Autowired
    private NativeEntityManager entityManager;

    /**
     * 分页对象用法
     *
     * @param demo
     * @param pageParam
     * @return
     */
    public Page<Function> findAll(Function demo, PageParam pageParam) {
        pageParam.setSort(new Sort(Sort.Direction.DESC, "createTime"));
        Criteria<Function> c = new Criteria<>();
        c.add(Restrictions.eq("path", demo.getPath(), true));
        c.add(Restrictions.eq("level", demo.getLevel(), true));
        c.add(Restrictions.eq("name", demo.getName(), true));
        return demoDao.findAll(c, pageParam);
    }

    /**
     * or的用法
     *
     * @param keys
     * @return
     */
    public List<Function> findAll(List<String> keys) {
        Criteria<Function> c = new Criteria<>();
        Criterion[] criterions = new Criterion[keys.size() * 2];
        for (int i = 0; i < keys.size(); i += 2) {
            criterions[i] = Restrictions.like("name", keys.get(i), true);
            criterions[i + 1] = Restrictions.like("path", keys.get(i), true);
        }
        c.add(Restrictions.or(criterions));
        return demoDao.findAll(c);
    }

    /**
     * 使用entityManager 写sql的方式
     *
     * @return
     */
    public List findAll() {
        return entityManager.find("select name,parent_id from function");
    }

    @Transactional
    public void add(Function function) {
        function.setId(null);
        function.setCreateTime(new Date());
        function.setName("测试444");
        function.setPath("#####");
        demoDao.save(function);
    }

    public List<Function> list() {
        return demoDao.findAll();
    }

    public Function findOne(Long id) {
        return demoDao.findOne(id);
    }
}
