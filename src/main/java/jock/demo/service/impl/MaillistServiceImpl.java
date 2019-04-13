package jock.demo.service.impl;

import jock.demo.dao.MaillistMapper;
import jock.demo.model.Maillist;
import jock.demo.service.BusinessException;
import jock.demo.service.MaillistService;
import jock.demo.service.ValidationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MaillistServiceImpl implements MaillistService {

    @Resource
    MaillistMapper maillistMapper;

    @Override
    public Maillist addMailAddress(String email) {
        if (email == null || "".equals(email.trim())) {
            throw new ValidationException("Email address cannot be empty!");
        }
        if (maillistMapper.selectByMail(email) != null) {
            throw new BusinessException("Email address already exists!");
        }
        Maillist maillist = new Maillist();
        maillist.setCreateDate(new Date());
        maillist.setEmail(email);
        maillistMapper.insert(maillist);
        return maillist;
    }

    @Override
    public void deleteMailAddress(String email) {
        if (email == null || "".equals(email.trim())) {
            throw new ValidationException("Email address cannot be empty!");
        }
        if (maillistMapper.selectByMail(email) == null) {
            throw new BusinessException("Email address does not exist!");
        }
        maillistMapper.deleteByEmail(email);
    }
}

