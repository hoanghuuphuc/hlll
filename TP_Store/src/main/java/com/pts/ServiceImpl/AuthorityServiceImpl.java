package com.pts.ServiceImpl;

import com.pts.DAO.AccountDAO;
import com.pts.DAO.AuthorityDao;
import com.pts.Service.AuthorityService;
import com.pts.entity.Account;
import com.pts.entity.Authority;
import com.pts.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDao authDao;
    @Autowired
    AccountDAO daoA;



}

