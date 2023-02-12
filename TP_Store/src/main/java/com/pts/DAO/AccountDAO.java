package com.pts.DAO;

import com.pts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account,String> {

    @Query("select p from Account p where p.tps_Username =?1")
    Account laytk(String username);
//    @Query("select p from Account p where p.tps_Gmail =?1")
//    Account layemail(String username);
    @Query("select p from Account  p where p.activationToken=?1")
    Account findByActivationToken(String token);

    @Query("select p from Account p where p.resetToken=?1")
    Account laytokenreset(String token);

}
