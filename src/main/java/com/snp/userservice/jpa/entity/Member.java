package com.snp.userservice.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "mem_id", nullable = false, length = 30)
    private String memId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "gender", nullable = false, length = 2)
    private String gender;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

}