package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesDemoTest {

    @Autowired
    private PropertiesDemo propertiesDemo;

    @Test
    public void testProperties(){
        Assert.assertEquals(propertiesDemo.getTitle(),"Learning_SpringBoot");
        Assert.assertEquals(propertiesDemo.getAuthor(),"Yomex");
    }

}