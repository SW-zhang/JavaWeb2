package test;

import com.alibaba.fastjson.JSON;
import com.framework.common.Properties;
import com.framework.querycore.PageParam;
import com.services.demo.dao.DemoDao;
import com.services.demo.entity.Demo;
import com.services.demo.servcie.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by wang on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class DemoServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoDao demoDao;

    @Autowired
    private Properties properties;

    @Test
    public void findAllTest() {
        Page<Demo> page = demoService.findAll(new Demo(), new PageParam());
        for (Demo f : page.getContent()) {
            System.out.println(f.toString());
        }
        assertTrue(page.getContent().size() > 0);
    }

    @Test
    public void addTest() {
        demoService.add(new Demo());
    }

    @Test
    public void envTest() throws InterruptedException {
        System.out.println();
        String username = properties.getValue("app", String.class);
        System.out.println("-------->" + username);
    }

    @Test
    public void envConfigTest() {
        System.out.println();
    }


    @Test
    public void demoDaoImplTest() {
        System.out.println(JSON.toJSONString(demoService.findAll()));
    }

    @Test
    public void cacheTest() {
        System.out.println(demoService.findOne(1L).toString());
        System.out.println(demoService.findOne(1L).toString());
    }

    @Test
    public void daoLikeTest() {
        Pageable pageable = new PageParam(0, 1);
        Page<Demo> page = demoDao.findAll(pageable);
        System.out.println(JSON.toJSONString(page));
    }
}
