package com.github.myoa.service;

import com.github.myoa.domain.User;
import com.github.myoa.domain.UserExample;
import com.github.myoa.dto.UserLoginDto;
import com.github.myoa.dto.UserRegistDto;
import com.github.myoa.mapper.UserMapper;
import com.github.myoa.util.CopyUtil;
import com.github.myoa.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Resource
    UserMapper userMapper;

    public void regist(UserRegistDto userRegist){
        String uuid = UuidUtil.getShortUuid();
        User user = CopyUtil.copy(userRegist, User.class);
        user.setId(uuid);
        userMapper.insert(user);
    }
    public UserLoginDto login(UserLoginDto userLogin){
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(userLogin.getUsername());
        List<User> list = userMapper.selectByExample(example);
        if(list.size()>0){
            UserLoginDto dto = CopyUtil.copy(list.get(0), UserLoginDto.class);
            return dto;
        }else {
            return null;
        }
    }
}
