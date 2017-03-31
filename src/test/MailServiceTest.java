package test;

import com.framework.mail.EmailParam;
import com.framework.mail.SendEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2016/12/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class MailServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SendEmailService emailService;

    @Test
    public void sendTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("user", "张树旺");
        Map<String, File> images = new HashMap<>();
        images.put("file", new File("C:\\Users\\wang\\Desktop\\111.png"));
        emailService.sendMessage(new String[]{"740787932@qq.com"}, "测试主题", "demo.vm", params, images, new File("C:\\Users\\wang\\Desktop\\remark"));
    }

    @Test
    public void sendTest2() {
        Map<String, Object> params = new HashMap<>();
        params.put("user", "张树旺");
        emailService.sendMessage(new String[]{"740787932@qq.com"}, "demo", params);
    }

    @Test
    public void sendTest3() {
        EmailParam param = new EmailParam();
        param.setReceivers(new String[]{"740787932@qq.com"});
        param.setTemplate("demo");
        emailService.sendMessage(param);
    }
}
