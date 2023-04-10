package com.example.findjobbe.model;

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
    @OneToOne
    private Account account;
    private String googleMapLink;
    private String imagePath;
    private String name;
    private String phoneNumber;
    private Long totalEmployee;//số lượng nv
    @ManyToOne
    private City city;
}
