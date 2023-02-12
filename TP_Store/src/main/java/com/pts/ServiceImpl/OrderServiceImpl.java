package com.pts.ServiceImpl;

import com.pts.DAO.OrderDAO;
import com.pts.Service.OrderService;
import com.pts.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public List<Order> myCrouse(String username) {
        return orderDAO.myCrouse(username);
    }
}
