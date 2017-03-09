package test;

import com.services.entity.Demo;
import com.services.servcie.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wang on 2016/12/26.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        try {
            ApplicationContext context = new FileSystemXmlApplicationContext("classpath:/applicationContext.xml");
            DemoService action = (DemoService) context.getBean("demoService");
            List<Demo> list = action.findAll(Arrays.asList("管理"));
            for (Demo f : list) {
                System.out.println(f.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
