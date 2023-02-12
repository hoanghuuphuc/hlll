package com.pts.Controller.site;

import com.pts.Service.OrderService;
import com.pts.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class myCrouseController {

    @Autowired
    OrderService orderService;
    @RequestMapping("/my-courses")
    public String index(Model m, HttpServletRequest request){
        String username=request.getRemoteUser();
        List<Order> mycrouse =orderService.myCrouse(username);
        m.addAttribute("mycrouse",mycrouse);
        return "site/myCrouse";
    }

}
