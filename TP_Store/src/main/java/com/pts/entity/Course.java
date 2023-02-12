package com.pts.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "course")
@Getter
@Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tps_id;
    String tps_Name;
    String tps_image;
    int tps_discount;
    int tps_Price;
    String tps_information;
    String tps_Teacher;
    Date tps_date = new Date();
    boolean tps_Status;
    @ManyToOne
    @JoinColumn(name ="tps_categoryid")
    private Category category;
    @OneToMany(mappedBy = "course")
    List<Chapter> chapters;
    @OneToMany(mappedBy = "courseor", fetch = FetchType.LAZY)
    List<Order> orders;

    @OneToMany(mappedBy = "courseCode")
    List<CourseCode> courseCodes;
    @Transient
    private String encodedId;

    // Getters và setters cho các trường

    public String getEncodedId() {
        return Base64.getEncoder().encodeToString(String.valueOf(tps_id).getBytes());
    }

    public void setEncodedId(String encodedId) {
        this.encodedId = encodedId;
    }


}
