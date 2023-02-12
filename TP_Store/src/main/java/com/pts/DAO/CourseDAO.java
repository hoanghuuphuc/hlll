package com.pts.DAO;

import com.pts.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDAO extends JpaRepository<Course,String> {
    //lay tat ca khoa hoc
    List<Course> findAll();

    //lay khoa hoc theo id (Trang chi tiet)
    @Query("select p from Course p where p.tps_id=?1")
    Course findById(int tps_id);


}
