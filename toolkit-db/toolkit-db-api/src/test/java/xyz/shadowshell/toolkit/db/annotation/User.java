package xyz.shadowshell.toolkit.db.annotation;

import java.io.Serializable;

import xyz.shadowshell.toolkit.db.api.annotation.Column;
import xyz.shadowshell.toolkit.db.api.annotation.Table;

/**
 * User
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
@Table("id_user")
public class User implements Serializable {

    /**
     * ID
     */
    @Column(value = "id", key = true)
    private Long id;

    /**
     * User id
     */
    @Column("user_id")
    private String userId;

    /**
     * User name
     */
    @Column("user_name")
    private String userName;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
