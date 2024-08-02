package com.bronya.projdemo.service.impl;

import com.bronya.projdemo.mapper.CategoryMapper;
import com.bronya.projdemo.dao.Category;
import com.bronya.projdemo.service.CategoryService;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public int insertCategory(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        category.setCreateUser((Integer) claims.get("id"));
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> selectCategoryList() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        return categoryMapper.selectCategoryList((Integer) claims.get("id"));
    }

    @Override
    public Category selectCategoryById(Integer id) {
        return categoryMapper.selectCategoryById(id);
    }

    @Override
    public int updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateCategory(category);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public int deleteCategoryById(Integer id) {
        // int rowCount = articleMapper.deleteArticleByCategoryId(id);
        // log.info("rowCount=", rowCount);
        return categoryMapper.deleteCategoryById(id);
    }
}
