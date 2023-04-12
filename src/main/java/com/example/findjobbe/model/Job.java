package com.example.findjobbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String description;// mô tả công việc cần tuyển
    private Long expiration;//kinh nghiệm
    private String gender;
    private Long quantity; // Số lượng cần tuyển
    private Double salaryMax;
    private Double salaryMin;
    private LocalDate startDate;//ngày đăng tuyển
    private Boolean status; //sử dụng để block bài đăng tuyển
    private String typeTime; //partTime or fullTime
    @ManyToOne
    private Career career;//ngành nghề, lĩnh vực
    @ManyToOne
    private City city;
    @ManyToOne
    private Employee employeeType; //chức vụ Dev, Director...
    @ManyToOne
    private Company company;//lấy địa chỉ và biết công ty nào tuyển

}
