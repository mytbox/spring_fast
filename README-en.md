# ✨An Efficient and Convenient Spring Boot General Controller Framework✨

## I. Introduction

In the Java development landscape, the repetitive task of writing basic code from scratch can be both time - consuming and resource - intensive. This framework, built upon Spring Boot and MyBatis - Plus, features a meticulously designed general controller class, `BaseController`. Its primary objective is to alleviate developers from the onerous burden of writing fundamental interfaces in their day - to - day development work.

## II. Core Advantages

### (I) One - click Inheritance for Instant Interfaces

By simply inheriting the common `BaseController` class, developers can rapidly acquire a comprehensive set of interface systems. There's no longer a need to painstakingly write create, read, update, and delete (CRUD) operation code line by line, thereby saving a significant amount of development time. Take the development of an interface for a new student - related table as an example:

Create a `Student` entity class and precisely define the fields corresponding to the database table.

Develop the service interface and its implementation class for the `Student` entity.

Construct a `StudentController` that inherits from `BaseController<Student>`.

Once these steps are completed, you'll immediately have access to a full range of interfaces for the student table, including CRUD, exact query, and fuzzy query capabilities.

### (II) Rich Built - in Query Functions

**Exact Query**: When an entity object with query conditions is passed in, the framework automatically iterates through the object's fields. Non - empty fields are then used as exact query conditions to precisely pinpoint the required data.

**Fuzzy Query**: For string - type fields, the framework offers a convenient fuzzy query function. By passing in an entity object, the framework automatically filters out string fields for fuzzy matching, catering to diverse query needs.

**Creation**: After inheriting `BaseController`, using the built - in creation method, developers can easily add data to the database by passing in a complete entity object in the request body. The framework takes care of all the details of interacting with the database to ensure accurate data insertion.

**Update**: When data in the database needs to be updated, the framework's update method is called. By passing in an entity object containing the fields to be updated and their new values in the request body, the framework locates the corresponding record in the database based on the unique identifier (such as an ID) in the entity object and updates it, ensuring data currency and accuracy.

**Deletion**: By invoking the framework's deletion method and passing in the unique identifier (such as an ID) of the record to be deleted, the framework promptly removes the corresponding record from the database, enabling efficient database data management.

### (III) Reliable and Scalable, Based on Popular Frameworks

This framework is powered by the robust Spring Boot and MyBatis - Plus frameworks. Spring Boot streamlines project setup and configuration, while MyBatis - Plus enhances the convenience of database operations. The synergy between the two not only guarantees the framework's efficient operation but also endows it with excellent scalability and maintainability.

## III. Usage Examples

Assume we need to create an interface for a `Student` table:

**Create an Entity Class**



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

**Create a Service Interface and Implementation Class**



```
import com.baomidou.mybatisplus.extension.service.IService;

public interface StudentService extends IService\<Student> {

}

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service

public class StudentServiceImpl extends ServiceImpl\<StudentMapper, Student> implements StudentService {

}
```

**Create a Controller Class**



```
@RestController

@RequestMapping("/students")

public class StudentController extends BaseController\<Student> {

          // Assume StudentService has been injected

          public StudentController(StudentService studentService) {

              super(studentService);

          }

}
```

## IV. Dependencies and Running Instructions

### (I) Maven Dependencies

Add the following dependencies to the project's `pom.xml` file:



```
<dependencies>

          <!-- Spring Boot Starter Web -->

          <dependency>

              <groupId>org.springframework.boot\</groupId>

              <artifactId>spring-boot-starter-web\</artifactId>

          </dependency>

          <!-- MyBatis - Plus Starter -->

          <dependency>

              <groupId>com.baomidou\</groupId>

              <artifactId>mybatis-plus-boot-starter\</artifactId>

              <version>\[latest version]\</version>

          </dependency>

          <!-- Other possible dependencies, such as database drivers -->

</dependencies>
```

### (II) Cloning the Repository and Running the Project

**Clone the Repository**: Execute the following command in the terminal to clone the project to your local machine.



```
git clone [repository URL]
```

**Run the Project**: Navigate to the project directory and execute the following command to start the project.



```
mvn install
```

Once the project is up and running, you can perform operations on student data through the corresponding interfaces.

## V. Conclusion

This framework serves as an efficient and convenient development tool for Java developers, significantly enhancing development efficiency and reducing development costs. Whether for the rapid iteration of small - scale projects or the infrastructure building of large - scale projects, it can play a crucial role. We invite you to give it a try and experience how it can make your development process easier and more enjoyable!