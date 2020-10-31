package tech.yangxm.sims.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("is_admin")
    //@TableLogic                 //当该字段在数据库中是数字时，将非0数字转成true，而0转成false
    private boolean isAdmin;

    @TableField("status")
    @EnumValue
    private Status status;

    @TableField("add_time")
    private Date addTime;

    @TableField("pwd_error_times")
    private int pwdErrorTimes;

    @TableField("last_change_pwd")
    private Date lastChangePwd;

    public enum Status {
        NORMAL,
        FREEZE,
        DELETE
    }
}
