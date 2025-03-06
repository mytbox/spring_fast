package com.csq.fweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("student")
@Schema(description = "学生实体类")
public class Student {
    @TableId(type = IdType.AUTO)
    @Schema(description = "学生 ID", example = "1")
    private Integer id;

    @Schema(description = "学生姓名", example = "张三")
    private String name;

    @Schema(description = "学生年龄", example = "20")
    private Integer age;
}