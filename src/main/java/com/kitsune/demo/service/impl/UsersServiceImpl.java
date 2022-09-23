package com.kitsune.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kitsune.demo.entity.Users;
import com.kitsune.demo.entity.vo.UserVo;
import com.kitsune.demo.mapper.UsersMapper;
import com.kitsune.demo.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kitsune
 * @since 2022-09-22
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    //获取分页数据
    @Override
    public void getPageData(Page<Users> page, UserVo userVo) {

        //new一个QueryWrapper来构造条件
        QueryWrapper<Users> wrapper = new QueryWrapper<>();

        //先获取属性值
        Integer age = userVo.getAge();
        String email = userVo.getEmail();
        String id = userVo.getId();
        String name = userVo.getName();

        //判断条件是否为空，如果不为空，则拼接条件
        //email和name可以模糊查询
        if(!StringUtils.isEmpty(age)) {
            wrapper.eq("age", age);
        }
        if(!StringUtils.isEmpty(email)) {
            wrapper.like("email", email);
        }
        if(!StringUtils.isEmpty(id)) {
            wrapper.eq("id", id);
        }
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }

        //调用mybatis的方法进行分页查询
        baseMapper.selectPage(page, wrapper);

    }
}
