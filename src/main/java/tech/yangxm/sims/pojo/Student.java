package tech.yangxm.sims.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("student")
public class Student implements Serializable {

    @TableId("stu_id")
    private String id;

    @TableField("stu_name")
    private String name;

    @TableField("stu_gender")
    private Gender gender;

    @TableField("stu_birthday")
    private Date birthday;

    @TableField("stu_class_id")
    private String classId;

    @TableField("stu_add_time")
    private Date addTime;

    public enum Gender{
        F,
        M
    }
}
