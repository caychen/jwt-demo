package com.caychen.jwt.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tm_user")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "id_card", nullable = false, length = 18)
    private String idCard;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "update_time", updatable = true)
    private Date updateTime;


}