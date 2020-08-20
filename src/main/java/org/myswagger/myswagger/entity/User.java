package org.myswagger.myswagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户实体")
@Data
public class User {
    @ApiModelProperty("ID")
    public String id;
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("年龄")
    public int age;
    @ApiModelProperty("密码")
    public String password;
}
