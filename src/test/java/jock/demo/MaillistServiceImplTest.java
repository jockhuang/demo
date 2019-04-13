package jock.demo;

import jock.demo.dao.MaillistMapper;
import jock.demo.service.BusinessException;
import jock.demo.service.MaillistService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaillistServiceImplTest {

    @Resource
    MaillistService maillistService;

    @Resource
    MaillistMapper maillistMapper;


    @Test
    public void mailOpTest(){
        String email = "test@abc.com";
        maillistService.deleteMailAddress(email);


        maillistService.addMailAddress(email);
        Assert.assertNotNull(maillistMapper.selectByMail(email));

        maillistService.deleteMailAddress(email);
        Assert.assertNull(maillistMapper.selectByMail(email));

    }

    /**
     * cannot have two identical records
     */
    @Test(expected = BusinessException.class)
    public void duplicateMail() {
        String email = "test@abc.com";

        maillistService.addMailAddress(email);
        maillistService.addMailAddress(email);
    }

    @Test(expected = BusinessException.class)
    public void addNullMail() {
        maillistService.addMailAddress(null);
    }

    @Test(expected = BusinessException.class)
    public void addEmptyMail() {
        maillistService.addMailAddress(" ");
    }
}
