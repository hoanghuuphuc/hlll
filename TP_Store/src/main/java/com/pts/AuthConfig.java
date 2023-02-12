    package com.pts;

    import com.pts.Service.UserService;
    import com.pts.ServiceImpl.AccountServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Configurable;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class AuthConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        AccountServiceImpl accountService;

        @Autowired
        UserService userService;
        @Autowired
        BCryptPasswordEncoder pe;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder() {
                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    // Mật khẩu không cần mã hóa
                    return rawPassword.toString().equals(encodedPassword);
                }
            };
        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userService);
        }
        /*--phan quyen su dung va hinh thuc dang nhap--*/
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // CDRP, CORS
            http.csrf().disable().cors().disable();

            // phan quyen su dung
            http.authorizeRequests()
    //		.antMatchers("/tps/admins").hasRole("ADMIN")
    //		.antMatchers("/home/users").hasAnyRole("ADMIN","USER")
    //		.antMatchers("/home/authenticated").authenticated()
                    .anyRequest().permitAll();// anonymous

            // dieu khien loi truy cap khong dung vai tro
            http.exceptionHandling().accessDeniedPage("/auth/signin");// [/error]
//â
            // giao dien dang nhap
            http.formLogin().loginPage("/").loginProcessingUrl("/auth/signin")// [/login]
                    .defaultSuccessUrl("/", false).failureUrl("/login/error")
                    .usernameParameter("username") // [username]
                    .passwordParameter("password");// [password]
            http.rememberMe().rememberMeParameter("remember"); // [remember-me]

            // dang xuat
            http.logout().logoutUrl("/auth/logoff")// [/logout]
                    .logoutSuccessUrl("/auth/signin");//chuyen trang

        }

    }
