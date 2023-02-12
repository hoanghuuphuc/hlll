package com.pts.ServiceImpl;

import com.pts.DAO.CategoryDAO;
import com.pts.Service.CategoryService;
import com.pts.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO cdao;

    @Override
    public List<Category> findAll() {
        return cdao.findAll();
    }
}
