package com.thunisoft;

import com.thunisoft.dao.TCodeSfMapper;
import com.thunisoft.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterdataApplicationTests {

    @Autowired
    private LoginService loginService;

    @Test
    public void contextLoads() {
        loginService.login("123","123");
    }

}
