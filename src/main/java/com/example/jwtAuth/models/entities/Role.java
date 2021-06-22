package com.example.jwtAuth.models.entities;

import com.example.jwtAuth.models.audits.AuditModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends AuditModel {

    public final static Long ROLE_ADMIN_ID = 1L;
    public final static Long ROLE_USER_ID = 2L;

    public final static String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public final static String ROLE_USER_NAME = "ROLE_USER";

    @Column(name = "name", unique = true)
    private String name;
}
