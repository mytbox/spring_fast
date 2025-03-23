# ✨高效便捷的 Spring Boot 通用控制器框架✨

## 一、简介

在 Java 开发中，重复性的基础接口编写工作常令人头疼。本框架基于 Spring Boot 与 MyBatis-Plus，精心构建通用控制器类`BaseController`，旨在为开发者排忧解难，极大减少繁琐的代码编写量。

## 二、核心优势

### （一）一键继承，接口立得

开发者仅需继承公用的`BaseController`类，即可迅速获得一套完备的接口体系，涵盖增删改查及精确、模糊查询等功能，无需手动编写大量基础代码，极大节省开发时间。以学生相关接口开发为例：

定义`Student`实体类，明确与数据库表对应的字段。

编写`Student`实体类对应的服务接口和实现类。

创建`StudentController`，继承`BaseController<Student>`。

完成上述步骤后，便立即拥有针对学生表的各类接口。

### （二）内置丰富查询功能

**精确查询**：传入包含查询条件的`Student`实体对象，框架自动遍历对象字段，将非空字段作为精确查询条件，精准定位所需数据。

**模糊查询**：对于字符串类型字段，框架自动筛选并进行模糊匹配，满足多样化查询需求。

### （三）基于流行框架，稳定可靠

本框架依托强大的 Spring Boot 和 MyBatis-Plus 框架。Spring Boot 简化项目搭建与配置，MyBatis-Plus 增强数据库操作便捷性。两者协同，确保框架高效运行，具备良好扩展性与维护性。

## 三、使用示例

假设要为`Student`表创建接口：

**创建实体类**：定义与数据库表对应的`Student`实体类，如：



```
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data

@TableName("student")

public class Student {

           private Long id;

           private String name;

           private Integer age;

}
```

**创建服务接口和实现类**：编写接口及实现类，实现类继承自 MyBatis-Plus 的`ServiceImpl`。

**创建控制器类**：



```
@RestController

@RequestMapping("/students")

public class StudentController extends BaseController<Student> {

           // 假设已注入StudentService

           public StudentController(StudentService studentService) {

               super(studentService);

           }

}
```

## 四、依赖与运行

### （一）Maven 依赖

在项目`pom.xml`文件中添加以下依赖：



```
<dependencies>

           <!-- Spring Boot Starter Web -->

           <dependency>

               <groupId>org.springframework.boot\</groupId>

               <artifactId>spring-boot-starter-web\</artifactId>

           </dependency>

           <!-- MyBatis-Plus Starter -->

           <dependency>

               <groupId>com.baomidou\</groupId>

               <artifactId>mybatis-plus-boot-starter\</artifactId>

               <version>最新版本\</version>

           </dependency>

           <!-- 其他可能的依赖，如数据库驱动等 -->

</dependencies>
```

### （二）克隆仓库与运行

**克隆仓库**：在终端执行以下命令，将项目克隆到本地。



```
git clone [仓库地址]
```

**运行项目**：进入项目目录，执行以下命令启动项目。



```
mvn install
```

项目启动后，即可通过相应接口对学生数据进行操作。

## 五、总结

本框架为 Java 开发者提供了高效便捷的开发工具，显著提升开发效率，降低开发成本。无论是小型项目快速迭代，还是大型项目基础架构搭建，均可发挥重要作用。欢迎尝试使用，让开发变得轻松愉悦！