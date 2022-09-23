package com.kitsune.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kitsune.demo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kitsune.demo.entity.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kitsune
 * @since 2022-09-22
 */
public interface UsersService extends IService<Users> {

    //获取分页数据
    void getPageData(Page<Users> page, UserVo userVo);

}
