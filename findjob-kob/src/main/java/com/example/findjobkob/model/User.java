package com.example.findjobkob.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = Account.class)
    private Account account;
    private String name;
    private String phoneNumber;
    private String imagePath;//ảnh ứng viên
    private String cvPath;//link chứa cv
}
