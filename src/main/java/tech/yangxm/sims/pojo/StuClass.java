package tech.yangxm.sims.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("class")
public class StuClass implements Serializable {

    @TableId("class_id")
    public String id;

    @TableField("class_name")
    private String name;

    @TableField("establishment")
    private Date establishment;
}
