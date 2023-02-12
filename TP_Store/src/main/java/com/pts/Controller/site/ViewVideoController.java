package com.pts.Controller.site;


import com.pts.DAO.ChapterDAO;
import com.pts.DAO.ContentDAO;
import com.pts.DAO.CourseDAO;
import com.pts.entity.Chapter;
import com.pts.entity.Content;
import com.pts.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ViewVideoController {
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    ContentDAO contentDAO;
    @RequestMapping("/learning/{id}")
    public String viewvideo(Model m, @PathVariable("id") String id) {
        byte[] decodedBytes = Base64.getDecoder().decode(id);
        int tps_id = Integer.parseInt(new String(decodedBytes));
        Course course = courseDAO.findById(tps_id);
        List<String> linkytbList = new ArrayList<>();
        for (Chapter chapter : course.getChapters()) {
            for (Content content : chapter.getContents()) {
                linkytbList.add(content.getTps_linkytb());
            }
        }
        m.addAttribute("linkytbList", linkytbList);
        m.addAttribute("ListMycourse", course);
        return "site/viewvideo";
    }

}
