package com.example.findjobkob.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = Account.class)
    private Account account;
    @ManyToOne
    private City city;
    private Long totalEmployee;
    private String address;
    private String imagePath;
    private String phoneNumber;
    private String googleMapLink;
}
