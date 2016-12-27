import com.alibaba.fastjson.JSON;
import com.framework.core.PageParam;
import com.services.dao.DemoDao;
import com.services.entity.Function;
import com.services.servcie.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by wang on 2016/12/26.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:/applicationContext.xml");
        DemoService action = (DemoService) context.getBean("demoService");
//        System.out.println(action.count("#"));
//        System.out.println(action.count(1));
//        System.out.println(action.findbyName("制度专栏"));
//        action.delete(1L);
//        System.out.println(action.findByUserId(1L));
//        Function d = new Function();
//        d.setPath("#");
//        Page<Function> page = action.findAll(d, new PageParam());
//        System.out.println(JSON.toJSONString(page));
//        System.out.println(JSON.toJSONString(action.findAll(Arrays.asList("#"))));
//        action.add(new Function());
//        System.out.println(JSON.toJSONString(action.findAll()));
//        action.add(new Function());
//        System.out.println(action.findOne(1L));
        System.out.println(JSON.toJSONString(action.findAll(new Function(), new PageParam())));
    }
}
