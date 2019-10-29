package com.downing.pojo.system;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author downing
 */
@Table(name="dg_permision")
public class Permision implements Serializable {

    @Id
    private Integer id;
    private String permisionKey;
    private String permisionName;
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermisionKey() {
        return permisionKey;
    }

    public void setPermisionKey(String permisionKey) {
        this.permisionKey = permisionKey;
    }

    public String getPermisionName() {
        return permisionName;
    }

    public void setPermisionName(String permisionName) {
        this.permisionName = permisionName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
