package test;

import com.alibaba.fastjson.JSON;
import com.services.jpa.web.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wang
 * @create 2017-03-16 15:19
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/applicationContext.xml",
        "classpath*:/springMVC-servlet.xml"})
public class DemoControllerTest {

    @Autowired
    private DemoController demoController;

    @Test
    public void listTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        System.out.println(mockMvc.perform(MockMvcRequestBuilders.get("/demo/list"))
                .andReturn().getResponse().getContentAsString());

        ModelAndView modelAndView = mockMvc.perform(MockMvcRequestBuilders.get("/demo/list_page"))
                .andReturn().getModelAndView();
        System.out.println();
        System.out.println(JSON.toJSONString(modelAndView.getModelMap().get("page")));

    }
}
