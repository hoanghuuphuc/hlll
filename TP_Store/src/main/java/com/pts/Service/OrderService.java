package com.pts.Service;

import com.pts.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    List<Order> myCrouse(String username);
}
