package com.example.demo.Controller;

import com.example.demo.bean.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")   // 通过这里配置使下面的映射都在/users下
public class UserController {

    //创建线程安全的Map
    static Map<Long,User> users = Collections.synchronizedMap(new HashMap<Long,User>());

    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method =  RequestMethod.GET)
    public List<User> getUsers(){
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> userList = new ArrayList<User>(users.values());
        return userList;
    }

    @ApiOperation(value = "创建用户",notes = "根据传入的user对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详细user",required = true,dataType = "User")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String PostUsers(@ModelAttribute User user){
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(),user);
        return "success";
    }

    @ApiOperation(value = "获取指定用户信息",notes = "根据url传入的id来获取指定用户的信息")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }


    @ApiOperation(value = "更新用户信息",notes = "根据传入的用户ID和user对象,更新指定用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "user",value = "用户详细user",required = true,dataType = "User")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id,@ModelAttribute User user){
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id,u);
        return "success";
    }

    @ApiOperation(value = "删除指定用户",notes = "根据传入的用户ID，删除指定用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }


}
