package com.pts.Controller.site;

import com.pts.DAO.ChapterDAO;
import com.pts.DAO.ContentDAO;
import com.pts.DAO.OrderDAO;
import com.pts.Service.AccountService;
import com.pts.Service.CategoryService;
import com.pts.Service.CourseService;

import java.util.Base64;
import com.pts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.Scanner;

@Controller
public class homeController {
    @Autowired
    AccountService accountService;
    @Autowired
    CourseService courseService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ContentDAO contentDAO;
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    OrderDAO orderDAO;

    @RequestMapping("/...")
    public String main(){
        return "/stie/index";
    }


    private String encodeId(Long id) {
        return Base64.getEncoder().encodeToString(String.valueOf(id).getBytes());
    }
    @RequestMapping("")
    public String index(Model m){
        List<Course> courses = courseService.findAll();

        for (Course course : courses) {
            course.setEncodedId(Base64.getEncoder().encodeToString(String.valueOf(course.getTps_id()).getBytes()));
        }

        m.addAttribute("courses",courses);

        return "/site/home";
    }
//    trang chi tiết


    @RequestMapping("/khoa-hoc/{tps_id}")
    public String trangchitiet(Model m, @PathVariable("tps_id") String id, HttpServletRequest request) throws FileNotFoundException {
        //tim kiếm nội dung theo id

            byte[] decodedBytes = Base64.getDecoder().decode(id);
            int tps_id = Integer.parseInt(new String(decodedBytes));
            Course course = courseService.findById(tps_id);
            course.setEncodedId(Base64.getEncoder().encodeToString(String.valueOf(course.getTps_id()).getBytes()));
            m.addAttribute("detail", course);

            String username=request.getRemoteUser();
//        System.out.println(username);
            Order order = orderDAO.ktKhoaHoc(username, tps_id);
            if (order != null) {
                m.addAttribute("owned", true);
            } else {
                m.addAttribute("owned", false);
            }

            //nội dụng chương
            List<Chapter> cc = chapterDAO.findByCourse(tps_id);
            m.addAttribute("listC", cc);

            //doc file
            int htt = tps_id;
            String url = "P:\\Code_QuanTrong\\Code_Khoa_Hoc\\backend\\TP_Store\\src\\main\\resources\\static\\noidung\\" + htt + ".txt";
            // Đọc dữ liệu từ File với Scanner
            try {
                FileInputStream fileInputStream = new FileInputStream(url);
                Scanner scanner = new Scanner(fileInputStream);
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    sb.append(line).append("\n");
                }
                String fileContent = sb.toString();
                m.addAttribute("fileContent", fileContent);
            } catch (FileNotFoundException e){
                System.out.println("loi");
            }
            //tong chuong
            int count=chapterDAO.findByCourse(tps_id).size();

            m.addAttribute("soluongChuong",count);

            //tong bai hoc
            int totalContent = 0;
            for (Chapter chapter : cc) {
                totalContent += chapter.getContents().size();
                m.addAttribute("totalContent",totalContent);
            }
            return "/site/details";

    }



//    @RequestMapping("/khoa-hoc")
//    public String chitiet(){
//        return "/site/details";
//
//    }
}
