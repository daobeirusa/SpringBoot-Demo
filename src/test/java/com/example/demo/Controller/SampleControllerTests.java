package com.example.demo.Controller;

import com.example.demo.Controller.SampleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

/*  直接在测试类上面加上如下2个注解
    @RunWith(SpringRunner.class)
    @SpringBootTest
    就能取到spring中的容器的实例，如果配置了@Autowired那么就自动将对象注入
* */
@RunWith(SpringRunner.class)  /*运行环境,SpringRunner 继承自 SpringJunit4ClassRunner,无任何扩展,仅为简写
                               *当一个类用@RunWith注释或继承一个用@RunWith注释的类时，JUnit将调用它所引用的类来运行该类中的测试而不是开发者去在junit内部去构建它。
                               */
@SpringBootTest
public class SampleControllerTests {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(new SampleController()).build();  //初始化MockMvc对象
    }

    @Test
    public void getHello() throws Exception{
        mvc.perform(MockMvcRequestBuilders  //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
                .get("/test")   //请求的ulr,方法是get
                .accept(MediaType.APPLICATION_JSON))    //数据的格式
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello ,World!")));
    }
}
