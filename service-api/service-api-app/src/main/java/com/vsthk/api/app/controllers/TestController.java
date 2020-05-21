package com.vsthk.api.app.controllers;

import com.vsthk.api.app.business.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by kenlent on 2020/1/7.
 */
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    DataSource dataSource;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/test0",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Date test0(
                    HttpServletRequest servletRequest) {
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select * from tab_User";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rSet = pstmt.executeQuery();
            while (rSet.next()) {
                String sid = rSet.getString("id");
                String name = rSet.getString("name");
                System.out.println(sid + "," + name);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new Date();
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Date test1(
            HttpServletRequest servletRequest) {
        userService.list();
        return new Date();
    }
}
