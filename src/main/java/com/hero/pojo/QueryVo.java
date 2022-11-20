package com.hero.pojo;

import com.hero.pojo.User;

//User包装类QueryVo
public class QueryVo {
    //当前页
    private Integer currentPage;
    //每页显示多少条
    private Integer pageSize;
    //用户
    private User user;
    //getter setter

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

