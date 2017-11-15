package com.mesen.controller;

import com.mesen.api.user.entity.User;
import com.mesen.vo.PageVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * 测试服务端失败：Fallback。
 *
 * Created by maosheng on 2017/11/15
 */
@Controller
public class UserFallbackTestController {

    @GetMapping("getuserlistbad")
    public PageVo getUserListBad(){
        PageVo userListGood = new PageVo();
        try{
            userListGood = getUserListGood(1L);
            throw new Exception("");
        }catch (Exception e){
            e.printStackTrace();
        }
        return userListGood;
    }

    @GetMapping("getuserlistgood")
    public PageVo<List<User>> getUserListGood(Long id){
        User user = new User(id,"a");
        User user2 = new User(id+1,"b");
        User user3 = new User(id+2,"c");
        List<User> list = Arrays.asList(user,user2,user3);

        return new PageVo(list);
    }
}
