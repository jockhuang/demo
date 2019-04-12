package jock.demo.model;


import java.io.Serializable;
import java.util.Date;


/**
 * Product info
 */
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * product id
     */
    private Integer id;
    /**
     * product name
     */
    private String name;
    /**
     * product description
     */
    private String description;
    /**
     * the flag of the product is released
     */
    private Boolean isRelease;
    /**
     * the create datetime of product
     */
    private Date createDate;
    /**
     * the update datetime of product
     */
    private Date updateDate;
    /**
     * the flag of product that it had been deleted in logic
     */
    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Boolean isRelease) {
        this.isRelease = isRelease;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
