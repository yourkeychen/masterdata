package com.thunisoft;

import com.thunisoft.dao.TCodeSfMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterdataApplicationTests {

    @Autowired
    private TCodeSfMapper tCodeSfMapper;

    @Test
    public void contextLoads() {
        String name = tCodeSfMapper.getName(190000);
        System.out.println(name);
        System.out.println("123");
    }

}
