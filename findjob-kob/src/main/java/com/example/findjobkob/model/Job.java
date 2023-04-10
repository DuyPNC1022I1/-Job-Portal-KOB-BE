package com.example.findjobkob.model;

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
    @ManyToOne
    private Career career;//ngành nghề
    @ManyToOne
    private City city;//thành phố
    @ManyToOne
    private Company company;//lấy tên công ty và địa điểm làm việc
    private Double salaryMin;//lương từ
    private Double salaryMax;//lương đến
    @ManyToOne
    private Employee employeeType;//chức vụ nhân viên
    private Long expiration;//kinh nghiệm
    private String typeTime;//parttime, fulltime
    private String description;//mô tả chi tiết
    private Long quantity;//số lượng tuyển
    private String gender;//giới tính
    private Date startDate;//ngày tạo
    private Boolean status;//trạng thái


}
