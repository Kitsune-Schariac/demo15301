package com.kitsune.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kitsune.demo.entity.Users;
import com.kitsune.demo.entity.vo.UserVo;
import com.kitsune.demo.service.UsersService;
import com.kitsune.demo.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kitsune
 * @since 2022-09-22
 */
@Api(tags = "users表数据控制器")
@RestController
@RequestMapping("/demo/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 获取所有数据
     * @return 所有数据
     */
    @ApiOperation(value = "获取所有数据")
    @GetMapping("getAllData")
    public R getData(){
        //调用service的方法，获取所有数据
        List<Users> list = usersService.list(null);
        //返回数据
        return R.ok().data("list", list);
    }

    /**
     * 增加数据
     * @param user user对象
     * @return 返回成功与否的消息
     */
    @ApiOperation(value = "增加一条数据")
    @PostMapping("addUser")
    public R addUser(
            @ApiParam(name = "user", value = "用户对象")
            @RequestBody Users user){
        //调取service的方法添加数据
        boolean save = usersService.save(user);
        if(save) {
            return R.ok().message("添加成功");
        }else {
            return R.error().message("添加失败");
        }

    }

    /**
     * 根据id删除
     * @param id id
     * @return 是否成功的消息
     */
    @ApiOperation(value = "根据id删除一条数据")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam(name = "id", value = "用户ID", required = true)
                    @PathVariable String id){

        //调取service的方法删除数据
        boolean r = usersService.removeById(id);

        if(r) {
            return R.ok().message("删除加成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    /**
     * 根据id查询数据
     * @param id id
     * @return 返回查询到的数据
     */
    @ApiOperation(value = "根据id查询一条数据")
    @GetMapping("getOneById/{id}")
    public R getOneById(@ApiParam(name = "id", value = "用户ID", required = true)
                    @PathVariable String id){

        //调取service的方法查询数据
        Users user = usersService.getById(id);

        return R.ok().data("user", user);
    }

    /**
     * 修改数据
     * @param user 修改的对象
     * @return 修改是否成功的消息
     */
    @ApiOperation(value = "修改一条数据")
    @PostMapping("updateUser")
    public R updateUser(
            @ApiParam(name = "user", value = "用户对象")
            @RequestBody Users user){
        //调取service的方法修改数据
        boolean save = usersService.updateById(user);
        if(save) {
            return R.ok().message("添加成功");
        }else {
            return R.error().message("添加失败");
        }

    }

    /**
     * 分页带条件查询数据
     * @param current 当前页
     * @param limit 每页的记录数
     * @param userVo 查询条件对象，name和email用于模糊匹配
     * @return 返回查询到的数据和表中的记录总数
     */
    @ApiOperation(value = "分页条件查询数据")
    @PostMapping("getPageData/{current}/{limit}")
    public R getPageData(@ApiParam(name = "current", value = "当前页", required = true)
                         @PathVariable long current,
                         @ApiParam(name = "limit", value = "页记录数", required = true)
                         @PathVariable long limit,
                         @ApiParam(name = "userVo", value = "业务对象")
                         @RequestBody UserVo userVo){

        //先new一个page对象
        Page<Users> page = new Page<>();
        //设置当前页和页记录数
        page.setCurrent(current);
        page.setSize(limit);

        //调用写在service里面的方法来进行分页查询
        usersService.getPageData(page, userVo);
        //取出总记录数和当前页的记录
        List<Users> records = page.getRecords();
        long total = page.getTotal();

        //返回数据
        return R.ok().data("total", total).data("records", records);


    }



}

