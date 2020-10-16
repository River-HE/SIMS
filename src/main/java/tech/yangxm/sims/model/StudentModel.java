package tech.yangxm.sims.model;

import lombok.Data;
import tech.yangxm.sims.exception.StudentParseException;
import tech.yangxm.sims.pojo.Student;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class StudentModel implements Serializable {

    private String id;

    private String name;

    private String gender;

    private String birthday;

    private String classId;

    public Student toStudent() throws StudentParseException {
        Student stu = new Student();
        stu.setId(this.getId());
        stu.setName(this.getName());
        if("男".equals(this.getGender())){
            stu.setGender(Student.Gender.M);
        }else if("女".equals(this.getGender())){
            stu.setGender(Student.Gender.F);
        }else{
            throw new StudentParseException("An exception occurred when trying to parse the field 'gender' of Student.");
        }
        try {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(this.getBirthday());
            stu.setBirthday(birthday);
        }catch (Exception e){
            throw new StudentParseException("An exception occurred when trying to parse the field 'gender' of Student.");
        }
        stu.setClassId(this.getClassId());
        return stu;
    }
}
