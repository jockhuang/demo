package jock.demo.service.impl;

import jock.demo.dao.MaillistMapper;
import jock.demo.model.Maillist;
import jock.demo.service.BusinessException;
import jock.demo.service.MaillistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MaillistServiceImpl implements MaillistService {

    @Resource
    MaillistMapper maillistMapper;

    @Override
    public boolean addMailAddress(String email) {
        if (maillistMapper.selectByMail(email) != null) {
            throw new BusinessException("Email address already exists!");
        }
        Maillist maillist = new Maillist();
        maillist.setCreateDate(new Date());
        maillist.setEmail(email);
        maillistMapper.insert(maillist);
        return true;
    }

    @Override
    public boolean deleteMailAddress(String email) {
        if (maillistMapper.selectByMail(email) == null) {
            throw new BusinessException("Email address does not exist!");
        }
        return maillistMapper.deleteByEmail(email) == 1;
    }
}

