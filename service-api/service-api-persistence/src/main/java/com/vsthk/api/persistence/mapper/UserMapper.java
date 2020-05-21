package com.vsthk.api.persistence.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vsthk.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by kenlent on 2020/1/8.
 */
@Mapper
@DS("slave")
public interface UserMapper extends BaseMapper<User> {
}
