package com.vsthk.api.app.business;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vsthk.api.entity.User;
import com.vsthk.api.persistence.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * Created by kenlent on 2020/1/8.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {
}
