package com.example.findjobbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchAll {
    private List<String> city;
    private List<String> typeTime;
    private List<String> expiration;
    private List<String> salary;
    private List<String> gender;
}
