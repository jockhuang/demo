package jock.demo;

import jock.demo.dao.MaillistMapper;
import jock.demo.service.BusinessException;
import jock.demo.service.MaillistService;
import jock.demo.service.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
public class MaillistServiceImplTest {

    @Resource
    MaillistService maillistService;

    @Resource
    MaillistMapper maillistMapper;


    @Test
    public void mailOpTest() {
        String email = "test@abc.com";
        maillistService.deleteMailAddress(email);


        maillistService.addMailAddress(email);
        Assertions.assertNotNull(maillistMapper.selectByMail(email));

        maillistService.deleteMailAddress(email);
        Assertions.assertNull(maillistMapper.selectByMail(email));

    }

    /**
     * cannot have two identical records
     */
    @Test
    public void duplicateMail() {
        String email = "test@abc.com";
        maillistService.addMailAddress(email);
        Assertions.assertThrows(BusinessException.class,()->maillistService.addMailAddress(email));
    }

    @Test
    public void addNullMail() {
        Assertions.assertThrows(ValidationException.class,()->maillistService.addMailAddress(null));
    }

    @Test
    public void addEmptyMail() {
        Assertions.assertThrows(ValidationException.class,()->maillistService.addMailAddress(" "));
    }
}
