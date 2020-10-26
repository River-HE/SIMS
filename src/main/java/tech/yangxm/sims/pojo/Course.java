package tech.yangxm.sims.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("course")
public class Course implements Serializable {

    @TableId("course_id")
    public String id;

    @TableField("course_name")
    private String name;

    @TableField("credit")
    private int credit;
}
