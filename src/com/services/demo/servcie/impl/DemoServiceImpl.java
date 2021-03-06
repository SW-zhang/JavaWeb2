package com.services.demo.servcie.impl;

import com.framework.base.BaseCRUDService;
import com.framework.base.BaseService;
import com.framework.querycore.Criteria;
import com.framework.querycore.Criterion;
import com.framework.querycore.PageParam;
import com.framework.querycore.Restrictions;
import com.services.demo.dao.DemoDao;
import com.services.demo.entity.Demo;
import com.services.demo.servcie.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DemoServiceImpl extends BaseService implements DemoService {

    @Autowired
    private DemoDao demoDao;
    @Autowired
    private BaseCRUDService entityManager;

    /**
     * 分页对象用法
     *
     * @param demo
     * @param pageParam
     * @return
     */
    public Page<Demo> findAll(Demo demo, PageParam pageParam) {
        pageParam.setSort(new Sort(Sort.Direction.DESC, "createTime"));
        Criteria<Demo> c = new Criteria<>();
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
    public List<Demo> findAll(List<String> keys) {
        Criteria<Demo> c = new Criteria<>();
        Criterion[] criterions = new Criterion[keys.size() * 2];
        for (int i = 0; i < keys.size(); i += 2) {
            criterions[i] = Restrictions.like("name", keys.get(i), true);
            criterions[i + 1] = Restrictions.like("path", keys.get(i), true);
        }
        c.add(Restrictions.or(criterions));
        return demoDao.findAll(c);
    }

    /**
     * 使用扩展接口的方法
     *
     * @return
     */
    public List findAll() {
        return demoDao.findAllTest();
    }

    @Transactional
    public Demo add(Demo demo) {
        demo.setId(null);
        demo.setCreateTime(new Date());
        demo.setName("测试444");
        demo.setPath("#####");
        return demoDao.save(demo);
    }

    public List<Demo> list() {
        return demoDao.findAll();
    }

    public Demo findOne(Long id) {
        return demoDao.findOne(id);
    }

    @Transactional
    public void remove(Long id) {
        demoDao.delete(id);
    }
}
