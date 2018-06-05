package com.bxin.Home.domain;


import com.bxin.Home.tools.entity.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BasePojo {

    @Id
    private Long id;

    private String account;

    private String password;

    @Column(name = "is_deleted")
    private Boolean deleted;
}
