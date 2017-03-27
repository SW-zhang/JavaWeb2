package test;

import com.alibaba.fastjson.JSON;
import com.services.demo.entity.MongoDemoEntity;
import com.services.demo.mongodb.DemoMongo;
import com.services.demo.servcie.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by wang on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class MongoDBTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DemoMongo demoMongo;
    @Autowired
    private DemoService demoService;

    @Test
    public void mongoDbTest() {
        MongoDemoEntity entity = new MongoDemoEntity();
        entity.setId("104");
        entity.setName("张树旺233");
        entity.setAge(12);
        demoMongo.save(entity);
    }

    @Test
    public void mongoListTest() {
        System.out.println(JSON.toJSONString(demoMongo.findList("旺233", 12)));
        System.out.println(JSON.toJSONString(demoService.findAll()));
    }

    @Test
    public void MongoLikeTest() {
        System.out.println(JSON.toJSONString(demoMongo.findByNameLike("旺")));
    }

    @Test
    public void gteTest() {
        System.out.println(JSON.toJSONString(demoMongo.findByGteAge(12)));
    }

    @Test
    public void findByNameAndAgeTest() {
        System.out.println(JSON.toJSONString(demoMongo.findByNameAndAge("旺", 11)));
    }

    @Test
    public void inTest() {
        System.out.println(JSON.toJSONString(demoMongo.findByAgeIn(Arrays.asList("101", "102"))));
    }
}
