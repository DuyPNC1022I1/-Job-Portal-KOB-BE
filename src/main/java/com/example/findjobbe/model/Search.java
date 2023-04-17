package com.example.findjobbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private Long expiration;
    private String gender;
    private Double salaryMax;
    private Double salaryMin;
    private String typeTime; //partTime or fullTime
    private Long city_id;
    private Long employeeType_id; //chức vụ Dev, Director...

}
