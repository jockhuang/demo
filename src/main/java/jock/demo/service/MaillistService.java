package jock.demo.service;

import jock.demo.model.Maillist;

public interface MaillistService {

    public Maillist addMailAddress(String email);

    public void deleteMailAddress(String email);

}
