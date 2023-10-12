package mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import mp.myenum.AgeEnum;
import mp.myenum.GradeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author caochenlei
 * @since 2020-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生年龄
     */
    private AgeEnum age;

    /**
     * 学生年纪
     */
    private GradeEnum grade;

    /**
     * 学生状态
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String status;

    /**
     * 是否删除（0:未删除、1:已删除）
     */
    private Integer deleted;


}
