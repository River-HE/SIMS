package tech.yangxm.sims.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("grade")
public class Grade implements Serializable {

    @TableId("stu_id")
    private String stuId;

    @TableId("cour_id")
    private String courId;

    @TableField("score")
    private int score;


}
