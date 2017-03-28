package test;

import com.alibaba.fastjson.JSON;
import com.framework.base.BaseCRUDService;
import com.services.demo.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by wang on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class BaseCRUDServiceImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private BaseCRUDService crudService;

    @Test
    public void listTest() {
        System.out.println(JSON.toJSONString(crudService.list(Demo.class)));
    }

    @Test
    public void getTest() {
        System.out.println(JSON.toJSONString(crudService.get(Demo.class, 1L)));
    }

    @Test
    public void saveOrUpdateTest() {
        Demo demo = crudService.get(Demo.class, 1L);
        demo.setPath("1111111");
        crudService.saveOrUpdate(demo);
    }

    @Test
    public void saveOrUpdateTest2() {
        Demo demo = new Demo();
        demo.setCreateTime(new Date());
        demo.setName("测试");
        demo.setPath("1111111");
        crudService.saveOrUpdate(demo);
    }

    @Test
    public void deleteTest() {
        crudService.delete(Demo.class, 18L);
    }

    @Test
    public void deleteTest2() {
        Demo demo = crudService.get(Demo.class, 16L);
        crudService.delete(demo);
    }

    @Test
    public void countTest() {
        System.out.println(crudService.count(Demo.class));
    }

    @Test
    public void hqlTest() {
        List<Demo> demos = (List<Demo>) crudService.hql("from Demo where path=?", "#####");
        for (Demo d : demos) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void hql2Test() {
        List<Demo> demos = crudService.hql(Demo.class, "from Demo where path = ?", "#####");
        for (Demo d : demos) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void sqlTest() {
        System.out.println(JSON.toJSONString(crudService.sql("select * from demo where path = ?", "#####")));
    }

    @Test
    public void sql2Test() {
        List<Demo> demos = crudService.sql(Demo.class, "select * from demo where path = ?", "#####");
        for (Demo d : demos) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void hqlUniueResultTest() {
        System.out.println(crudService.uniqueResultHql("from Demo where id = ?", 1L).toString());
    }

    @Test
    public void hqlUniueResult2Test() {
        System.out.println(crudService.uniqueResultHql(Demo.class, "from Demo where id = ?", 1L).toString());
    }

    @Test
    public void sqlUniueResultTest() {
        System.out.println(JSON.toJSONString(crudService.uniqueResultSql("select * from demo where id = ?", 1L)));
    }

    @Test
    public void sqlUniueResult2Test() {
        System.out.println(crudService.uniqueResultSql(Demo.class, "select * from demo where id = ?", 1L).toString());
    }

    @Test
    public void hqlTopResultTest() {
        Demo demo = (Demo) crudService.topResultHql("from Demo");
        System.out.println(demo.toString());
    }

    @Test
    public void sqlTopResultTest() {
        System.out.println(JSON.toJSONString(crudService.topResultSql("select * from demo")));
    }

    @Test
    public void sqlTopResult2Test() {
        Demo demo = crudService.topResultSql(Demo.class, "select * from demo");
        System.out.println(demo.toString());
    }

    @Test
    public void sqlTopLimitResultTest() {
        List<Demo> demos = crudService.topResultSql(Demo.class, 5, "select * from demo");
        for (Demo demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void hqlTopLimitResultTest() {
        List<Demo> demos = crudService.topResultHql(Demo.class, 5, "from Demo");
        for (Demo demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void hqlUpdateTest() {
        crudService.updateHql("update Demo set level = ?,parent_id = ?", 2, 1L);
    }

    @Test
    public void sqlUpdateTest() {
        System.out.println(crudService.updateSql("update demo set level = ?,parent_id = ? where id = ?", 1, 2L, 21L));
    }

    @Test
    public void hqlPagerTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("path", "#####");
        List<Demo> demos = crudService.hqlPager(Demo.class, params, Arrays.asList(
                new Sort.Order(Sort.Direction.ASC, "createTime")), 1, 5);
        for (Demo demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void hqlPager2Test() {
        List<Demo> demos = (List<Demo>) crudService.hqlPager("from Demo where path = ? order by createTime desc", 1, 5, "#####");
        for (Demo demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void sqlPager2Test() {
        List<Demo> demos = (List<Demo>) crudService.sqlPager("select * from demo where path = ? order by create_time desc", 1, 5, "#####");
        System.out.println(JSON.toJSONString(demos));
    }

    @Test
    public void countHqlTest() {
        System.out.println(crudService.countHql("select count(*) from Demo where path=?", "#####"));
    }

    @Test
    public void countSqlTest() {
        System.out.println(crudService.countSql("select count(*) from demo where path=?", "#####"));
    }

    @Test
    public void countHql2Test() {
        Map<String, Object> params = new HashMap<>();
        params.put("path", "#####");
        System.out.println(crudService.count(Demo.class, params));
    }

}
