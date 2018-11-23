package com.example.demo.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception{
        RequestBuilder request = null;

        //get查询user列表，应该为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //post提交一个user
        request = post("/users/")
                .param("id","1")
                .param("name","Yomex")
                .param("age","22");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //获取user列表
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"Yomex\",\"age\":22}]")));

        //get一个id为1的user
        request = get("/users/1/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Yomex\",\"age\":22}")));

        //put修改id为1的user
        request = put("/users/1/")
                .param("id","1")
                .param("name","Rika")
                .param("age","15");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //查询修改结果
        request = get("/users/1/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Rika\",\"age\":15}")));

        //删除一个user
        request = delete("/users/1/");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        //users应为空
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }
}