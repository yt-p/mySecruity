package com.yt.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yt.exception.UserNotExistException;
import com.yt.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


/**
 * @Description:  swagger自动生成文档
 *   http://localhost:8080/swagger-ui.html
 * @Auther: yt
 * @Date: 2020/4/5 0005 21:47
 */
@RestController
public class UserController {
    @RequestMapping(value = "/user")
    public int query(@RequestParam(value = "name") String userName,@PageableDefault(page = 2,size = 10,sort = {"userName"},direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println(userName);
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        return 0;

    }
    /*id只能获取数字*/
    @RequestMapping(value = "/user1/{id:\\d+}")
    public String query1(@PathVariable(value = "id") int id){
        return String.valueOf(id);

    }
    @RequestMapping(value = "/user2")
    //选择返回不同的view UserDetailView 继承 UserSimpleView
    //UserDetailView继承UserSimpleView所有视图
    @JsonView(User.UserDetailView.class)
    public User query2(){
        return new User("name","密码",1);
    }
    @RequestMapping(value = "/user3")
    public String query3(@Valid User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getFieldErrors().stream().forEach(error -> System.out.println(error.getField()+error.getDefaultMessage()));
        }
        System.out.println(user.getBirthday());
        return user.getUserName();
    }
    @GetMapping(value = "/user/{id}")
    @ApiOperation(value = "查询用户信息") //在swagger文档中相当于方法别名/注释
    public User getInfo(@ApiParam("用户id") //在swagger文档中相当于参数别名/注释
                        @PathVariable(value = "id") String id){
        throw new UserNotExistException(id);

    }
}
