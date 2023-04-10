package com.example.findjobbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;//mổ ta
    private Long expiration;//kinh nghiệm
    private String gender;
    private Long quantity;
    private Double salaryMax;
    private Double salaryMin;
    private Date startDate;//ngày tuỷen
    private Boolean status;
    private String typeTime;//parttime or fulltime
    @ManyToOne
    private Career career;//ngành nghề
    @ManyToOne
    private City city;
    @ManyToOne
    private Employee employeeType;//chức vụ
    @ManyToOne
    private Company company;//lấy địa chỉ và biết công ty nào tuyển

}
