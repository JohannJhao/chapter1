package com.antsix.web;

import com.antsix.domain.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(value = "用户相关接口",tags = {"详解"},hidden = true)
@RequestMapping(value = "/users")
public class UserController {
    // 创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,User>());

    @ApiOperation(value = "获取用户列表",notes = "注意别出错")
    @RequestMapping(value = {""},method = RequestMethod.GET)
    @GetMapping
    public List<User> getUserList(){
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        System.out.println(Thread.currentThread().getName()+" ###### Method GET 获取列表 :"+r.size());
        return r;
    }

    @ApiOperation(value = "创建用户",notes = "注意别出错")
    @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User")
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        System.out.println(Thread.currentThread().getName()+" ###### Method POST 创建User :"+user.toString());
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="注意别出错")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        System.out.println(id);
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        if(users.size()==0){
            return new User();
        }
        System.out.println(Thread.currentThread().getName()+" ###### Method GET 获取单个User :"+users.get(id).toString());
        return users.get(id);
    }


    @ApiOperation(value="更新用户详细信息", notes="注意别出错")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        System.out.println(Thread.currentThread().getName()+" ###### Method PUT 更新指定User :"+user.toString()+"。 Update id : "+id);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="注意别出错")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        System.out.println(Thread.currentThread().getName()+" ###### Method DELETE 删除指定User :"+id);
        return "success";
    }
}
