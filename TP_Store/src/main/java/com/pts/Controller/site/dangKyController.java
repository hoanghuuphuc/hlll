package com.pts.Controller.site;

import com.pts.DAO.AccountDAO;
import com.pts.Service.UserService;
import com.pts.ServiceImpl.MailerServiceImpl;
import com.pts.ServiceImpl.UserServiceIml;
import com.pts.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.Date;

@Controller
public class dangKyController {

    @Autowired
    AccountDAO accountDAO;
    @Autowired
    private UserService userService;

    @Autowired
    MailerServiceImpl mailer;
    @Autowired
    UserServiceIml userServiceIml;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("auth/signup")
    public String signup(Model m, Account account){
//        m.addAttribute("acc",account);
        return "site/dangky";
    }
    @PostMapping("/dang-ky")
    public  String dangky(Model m,
                          @RequestParam("tps_Password") String password,
                          @RequestParam("tps_Gmail") String username) throws MessagingException {
        // kiem tra tai khoan đã sử dụng chưa
        Account acc = accountDAO.laytk(username);
        if (acc != null) {
            if (acc.getTps_Active()) {
                // tai khoan da active
                m.addAttribute("error", "Tên đăng nhập đã được sử dụng");

                return "/site/dangky";
            } else {

                acc.setTps_Password(password);
                accountDAO.save(acc);

                String activationUrl = "http://localhost:8080/activate?token=" + acc.getActivationToken();
                m.addAttribute("error", "Tài Khoản đang chờ kích hoạt Vui lòng kiểm tra gmail!");
                // tai khoan chua active
                mailer.send(username,"Kích Hoạt Tài Khoản","Xin Chào  Chúng tôi đã nhận được yêu cầu đăng nhập vào CrouseOnline bằng địa chỉ email này. Nếu bạn muốn đăng nhập bằng tài khoản "+acc.getTps_Username()+" của mình, hãy nhấp vào liên kết:"+"<a href='" + activationUrl + "'>tại đây</a>");
                return "site/dangky";
            }
        }

        // kiem tra email đã sử dụng chưa
//        acc = accountDAO.layemail(gmail);
//        if (acc != null) {
//            if (acc.getTps_Active()) {
//                // email da duoc su dung
//                m.addAttribute("error", "Email đã được sử dụng");
//                System.out.println("3");
//                return "/site/dangky";
//            } else {
//                // email chua duoc active
//                m.addAttribute("error", "Tài khoản của bạn đang chờ được kích hoạt, vui lòng kiểm tra email");
//                System.out.println("4");
//                return "/site/dangky";
//            }
//        }

        // tao moi tai khoan
        acc = new Account();
        acc.setTps_Username(username);
//        acc.setTps_Password(passwordEncoder.encode(password));
        acc.setTps_Password(password);
        acc.setTps_Active(false);
        acc.setTps_Date(new Date());
        acc.setTps_Photo("1.jpg");
        acc.generateActivationToken();
        userServiceIml.save(acc);
        String activationUrl = "http://localhost:8080/activate?token=" + acc.getActivationToken();

        mailer.send(username,"Kích Hoạt Tài Khoản","Xin Chào  Chúng tôi đã nhận được yêu cầu đăng nhập vào CrouseOnline bằng địa chỉ email này. Nếu bạn muốn đăng nhập bằng tài khoản "+acc.getTps_Username()+" của mình, hãy nhấp vào liên kết:"+"<a href='" + activationUrl + "'>tại đây</a>");
        return "site/dangky";

    }

    @GetMapping("/activate")
    public String activate(Model m, @RequestParam("token") String token) {
        Account acc = accountDAO.findByActivationToken(token);
        if (acc != null) {
            acc.setTps_Active(true);
            acc.setActivationToken(null); // clear the activation token
            accountDAO.save(acc);
            m.addAttribute("error","Kích Hoạt Tài Khoản Thành Công");
            return "site/dangnhap";
        } else {
            m.addAttribute("error", "Mã Token kích hoạt không hợp lệ");
            return "site/dangky";
        }
    }

}
