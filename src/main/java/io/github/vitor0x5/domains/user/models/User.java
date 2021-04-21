package io.github.vitor0x5.domains.user.models;

import io.github.vitor0x5.domains.EntityWithUUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends EntityWithUUID {
    private String name;
    private String email;
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

}

