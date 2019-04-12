package jock.demo;

import jock.demo.dao.MaillistMapper;
import jock.demo.service.MaillistService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaillistServiceImpl {

    @Resource
    MaillistService maillistService;

    @Resource
    MaillistMapper maillistMapper;

    @Test
    public void mailOpTest(){
        String email = "test@abc.com";

        maillistService.addMailAddress(email);
        Assert.assertNotNull(maillistMapper.selectByMail(email));

        maillistService.deleteMailAddress(email);
        Assert.assertNull(maillistMapper.selectByMail(email));

    }
}
