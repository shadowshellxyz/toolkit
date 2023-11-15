package xyz.shadowshell.toolkit.db.orm;

import java.io.Serializable;

import net.shadowdefender.toolkit.db.api.annotation.Column;
import net.shadowdefender.toolkit.db.api.annotation.Entity;

/**
 * OrmTestBean
 *
 * @author shadow
 */
@Entity("test_user")
public class OrmTestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(value = "id", key = true)
    private Long id;

    @Column("user_id")
    private String userId;

    public OrmTestBean() {
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
}