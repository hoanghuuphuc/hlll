package com.pts.DAO;

import com.pts.entity.CourseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseCodeDAO extends JpaRepository<CourseCode,Integer> {
    @Query("select p from CourseCode p where p.code =?1")
    CourseCode laymacode(String code);
}
