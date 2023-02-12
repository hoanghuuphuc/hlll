package com.pts.DAO;

import com.pts.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer> {
    @Query("select p from Order p WHERE p.tps_username =?1 and p.courseor.tps_id =?2")
    Order kiemtracode(String username,Integer courseId);

    @Query("select p from Order p where p.tps_username=?1")
    List<Order> myCrouse(String username);

    @Query("select p from Order p where p.tps_username=?1 and p.courseor.tps_id=?2")
    Order ktKhoaHoc(String username,int id);
}
