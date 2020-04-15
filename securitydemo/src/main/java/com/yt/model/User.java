package com.yt.model;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Description:
 * @Auther: yt
 * @Date: 2020/4/5 0005 21:45
 */
public class User {
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};
    @ApiModelProperty(value = "用户生日") //在swagger文档中相当于属性别名/注释
    private Date birthday;
    @NotBlank(message = "姓名不能为空")
    private String userName;
    @Past(message = "生日必须是过去的时间")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String password;
    private int age;

    public User(String userName, String password, int age) {
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public User() {
    }
    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
