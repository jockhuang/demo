package jock.demo.model;


import java.io.Serializable;
import java.util.Date;


/**
 * Maillist
 */
public class Maillist implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * email address
     */
    private String email;
    /**
     * the record create datetime
     */
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
