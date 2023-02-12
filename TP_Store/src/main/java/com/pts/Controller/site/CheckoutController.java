package com.pts.Controller.site;

import com.pts.DAO.CourseDAO;
import com.pts.DAO.OrderDAO;
import com.pts.entity.Course;
import com.pts.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;


@Controller
public class CheckoutController {
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    OrderDAO orderDAO;
    @RequestMapping("/checkout/{id}")
    private String index(Model m, @PathVariable("id")String id){

        byte[] decodedBytes = Base64.getDecoder().decode(id);
        int tps_id = Integer.parseInt(new String(decodedBytes));
        Course course =courseDAO.findById(tps_id);
        m.addAttribute("checkout",course);
        return "/site/checkout";

    }
    @GetMapping("/add-course/{id}")
    public String themkhoahoc(Model m, @PathVariable("id")String id, HttpServletRequest request){
        byte[] decodedBytes = Base64.getDecoder().decode(id);
        int tps_id = Integer.parseInt(new String(decodedBytes));
        Course course=courseDAO.findById(tps_id);
        String username=request.getRemoteUser();
        Order order = new Order();
        order.setTps_username(username);
        order.setCourseor(course);
        order.setTps_statuscode(false);
        orderDAO.save(order);
        return "redirect:/learning/{id}";
    }
}
