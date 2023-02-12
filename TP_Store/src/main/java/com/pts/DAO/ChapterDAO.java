package com.pts.DAO;

import com.pts.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterDAO extends JpaRepository<Chapter,String> {
    @Query("select p from Chapter p where p.course.tps_id = ?1")
    List<Chapter>findByCourse(int id);







}
