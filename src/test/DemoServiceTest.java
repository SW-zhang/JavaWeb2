package test;

import com.framework.querycore.PageParam;
import com.framework.common.Properties;
import com.services.entity.Demo;
import com.services.servcie.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
}
