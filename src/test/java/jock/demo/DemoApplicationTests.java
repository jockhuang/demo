package jock.demo;

import jock.demo.dao.MaillistMapper;
import jock.demo.model.Maillist;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DemoApplicationTests {

    @Resource
    MaillistMapper maillistMapper;

    @Test
    public void demo() {

        List<Maillist> demoEntities = maillistMapper.selectAll();
        for (Maillist demoEntity : demoEntities) {
            System.out.println(demoEntity.getId());
        }

    }

    @Test
    public void contextLoads() {
    }

}
