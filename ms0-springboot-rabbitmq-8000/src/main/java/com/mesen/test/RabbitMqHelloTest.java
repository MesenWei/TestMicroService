package com.mesen.test;

import com.mesen.sender.SendTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by RabbitMqHelloTest on 2018/1/8.
 */
@RestController
public class RabbitMqHelloTest {
    @Autowired
    private SendTest sendTest;

    @RequestMapping("hello")
    public void hello() throws Exception {
        sendTest.send();
    }
}
