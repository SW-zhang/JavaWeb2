package com.services.servcie;

import com.framework.core.Criteria;
import com.framework.core.Criterion;
import com.framework.core.PageParam;
import com.framework.core.Restrictions;
import com.framework.service.BaseService;
import com.framework.service.NativeEntityManager;
import com.services.dao.DemoDao;
import com.services.entity.Demo;
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
     * 使用entityManager 写sql的方式
     *
     * @return
     */
    public List findAll() {
        return entityManager.find("select name,parent_id from demo");
    }

    @Transactional
    public void add(Demo demo) {
        demo.setId(null);
        demo.setCreateTime(new Date());
        demo.setName("测试444");
        demo.setPath("#####");
        demoDao.save(demo);
    }

    public List<Demo> list() {
        return demoDao.findAll();
    }

    public Demo findOne(Long id) {
        return demoDao.findOne(id);
    }
}
