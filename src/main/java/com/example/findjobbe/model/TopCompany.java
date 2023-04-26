package com.example.findjobbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopCompany {
    private Company company;
    private Long totalQuantity;
    private Integer totalJob;
}
