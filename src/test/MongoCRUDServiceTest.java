package test;

import com.alibaba.fastjson.JSON;
import com.framework.base.impl.MongoCRUDServiceImpl;
import com.services.demo.entity.MongoDemoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wang on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class MongoCRUDServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MongoCRUDServiceImpl crudService;

    @Test
    public void findAllTest() {
        List<MongoDemoEntity> demos = crudService.findAll(MongoDemoEntity.class);
        for (MongoDemoEntity demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void findTest() {
        Criteria where = Criteria.where("age").is(12);
        List<MongoDemoEntity> demos = crudService.find(MongoDemoEntity.class, where);
        for (MongoDemoEntity demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void findOneTest() {
        System.out.println(crudService.findOne(MongoDemoEntity.class, Criteria.where("id").is("105")).toString());
    }

    @Test
    public void findByIdTest() {
        System.out.println(crudService.findById(MongoDemoEntity.class, "102").toString());
    }

    @Test
    public void countTest() {
        System.out.println(crudService.count(MongoDemoEntity.class, Criteria.where("age").is(12)));
    }

    @Test
    public void saveTest() {
        crudService.save(new MongoDemoEntity());
    }

    @Test
    public void save2Test() {
        crudService.save(Arrays.asList(new MongoDemoEntity(), new MongoDemoEntity(), new MongoDemoEntity(), new MongoDemoEntity()));
    }

    @Test
    public void deleteTest() {
        crudService.delete(crudService.findById(MongoDemoEntity.class, "58db684ec9b6f927b8ba2f94"));
    }

    @Test
    public void delete2Test() {
        crudService.delete(MongoDemoEntity.class, Criteria.where("age").exists(false));
    }

    @Test
    public void updateTest() {
        Update update = new Update();
        update.set("age", 10);
        update.set("name", "测试");
//        Update.update("age", "15").set("name", "测试");
        crudService.updateFirst(MongoDemoEntity.class, Criteria.where("name").is("测试"), update);
    }

    @Test
    public void update2Test() {
        Update update = new Update();
        update.set("age", "115");
        update.set("name", "测试111");
//        Update.update("age", "15").set("name", "测试");
        crudService.update(MongoDemoEntity.class, Criteria.where("id").is("58db6f8fc9b6f9278c668263"), update);
    }

    @Test
    public void pagerTest() {
        List<MongoDemoEntity> demos = crudService.pager(MongoDemoEntity.class, 0, 20, new Sort(
                new Sort.Order(Sort.Direction.DESC, "id")
        ));
        for (MongoDemoEntity demo : demos) {
            System.out.println(demo.toString());
        }
    }

    @Test
    public void groupTest() {
        GroupBy groupBy = GroupBy.key("age").initialDocument("{count:0,group_names:''}")
                .reduceFunction("function(doc, result){result.count+=1;result.group_names += ',' + doc.name}");
        GroupByResults<MongoDemoEntity> results = crudService.group(MongoDemoEntity.class, groupBy);
        System.out.println(JSON.toJSONString(results));
    }

    @Test
    public void group2Test() {
        GroupBy groupBy = GroupBy.key("age").initialDocument("{count:0}").reduceFunction("function(doc, prev){prev.count+=1;}");
        GroupByResults<MongoDemoEntity> results = crudService.group(MongoDemoEntity.class, Criteria.where("age").is(12), groupBy);
        System.out.println(JSON.toJSONString(results));
    }

}
