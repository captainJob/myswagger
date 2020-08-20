package org.myswagger.myswagger.controller;

import io.swagger.annotations.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.myswagger.myswagger.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Api(tags = "xxx模块说明")	作用在模块类上
 * @ApiOperation("xxx接口说明") 作用在接口方法上
 * @ApiModel("xxxPOJO说明") 作用在模型类上：如VO、BO
 * @ApiModelProperty(value = "xxx属性说明",hidden = true)	作用在类方法和属性上，hidden设置为true可以隐藏该属性
 * @ApiParam("xxx参数说明") 作用在参数、方法和字段上，类似@ApiModelProperty
 *
 *
 * 对象属性	@ApiModelProperty	用在出入参数对象的字段上
 * 协议集描述	@Api	用于controller类上
 * 协议描述	@ApiOperation	用在controller的方法上
 * Response集	@ApiResponses	用在controller的方法上
 * Response	@ApiResponse	用在 @ApiResponses里边
 * 非对象参数集	@ApiImplicitParams	用在controller的方法上
 * 非对象参数描述	@ApiImplicitParam	用在@ApiImplicitParams的方法里边
 * 描述返回对象的意义	@ApiModel	用在返回对象类上
 * https://blog.csdn.net/xupeng874395012/article/details/68946676
 *
 */
@Api(tags = "xxx模块说明")
@RestController
@RequestMapping("mySwagger")
public class HelloController {
    @GetMapping("/sayHello")
    @ApiOperation("xxx接口说明")
    public String sayHello(@ApiParam("用户名") String name){
        return "hello,"+name;
    }
    @GetMapping("/getUser")
    @ApiOperation("获取用户")
    public User getUser(){
        User user = new User();
        user.setUsername("xiaoming");
        user.setPassword("123456");
        return user;
    }
    @GetMapping("/getUserByName")
    @ApiOperation("根据用户名获取用户")
    public User getUserByName(@ApiParam(name = "name",value = "用户名")String name){
        User user = new User();
        user.setUsername(name);
        user.setPassword("1234567");
        return user;
    }

    /**
     * Postman处理@RequestBody请求
     * 选择Post -- Body  -- raw  -- JSON(application/json)
     * @param user
     * @return
     */
    @ApiOperation("增加用户")
    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody @ApiParam("用户信息")User user){
        return user;
    }

    @GetMapping("/getUserById")
    @ApiOperation(value="获取人员信息", notes="根据ID获取人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "id", value = "主键id", required = true, dataType = "String"),
    })
    public User getUserById(String id,HttpServletRequest request) throws Exception{
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        if(token==null||"".equals(token)){
            throw new Exception("没有权限操作");
        }
        User user = new User();
        user.setId(id);
        user.setUsername("主键id");
        user.setPassword("1234567");
        return user;
    }
}
