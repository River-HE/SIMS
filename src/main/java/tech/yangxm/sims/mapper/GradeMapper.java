package tech.yangxm.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tech.yangxm.sims.pojo.Grade;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<Grade> {

    //    1.学生单科成绩
//        SELECT S.STU_ID, S.STU_NAME, C.COUR_ID, C.COUR_NAME, G.SCORE
//        FROM GRADE G, STUDENT S, COURSE C
//        WHERE G.STU_ID=S.STU_ID AND G.COUR_ID=C.COUR_ID AND S.STU_ID='stu001'
//    2.学生总成绩
//        单个学生
//    SELECT S.STU_ID,S.STU_NAME, SUM(G.SCORE) AS TOTALSCORE FROM GRADE G, STUDENT S WHERE G.STU_ID=S.STU_ID AND S.STU_ID='stu001'
//        所有学生
//        SELECT S.STU_ID, S.STU_NAME, SUM(G.SCORE) AS TOTALSCORE
//        FROM GRADE G, STUDENT S
//        WHERE G.STU_ID=S.STU_ID
//        GROUP BY S.STU_ID
//    3.班级单科平均分
//        SELECT CL.CLASS_ID, CL.CLASS_NAME,  C.COUR_NAME, ROUND(AVG(G.SCORE),1) AS AVGSCORE
//        FROM GRADE G, STUDENT S, COURSE C, CLASS CL
//        WHERE G.STU_ID=S.STU_ID AND G.COUR_ID=C.COUR_ID AND S.STU_CLASS_ID=CL.CLASS_ID
//        GROUP BY CL.CLASS_ID, C.COUR_NAME
//    4.班级总平均分
//        SELECT S.STU_CLASS_ID, ROUND(SUM(G.SCORE)/COUNT( DISTINCT S.STU_ID) ,1) AS AVGSCORE
//        FROM GRADE G, STUDENT S
//        WHERE G.STU_ID=S.STU_ID
//        GROUP BY S.STU_CLASS_ID

    @Select("SELECT S.STU_ID, S.STU_NAME, C.COUR_ID, C.COUR_NAME, G.SCORE " +
            "FROM GRADE G, STUDENT S, COURSE C " +
            "WHERE G.STU_ID=S.STU_ID AND G.COUR_ID=C.COUR_ID AND S.STU_ID=#{stuId}")
    List<Map<String, Object>> findGradesByStuId(@Param("stuId") String stuId);

    @Select("SELECT S.STU_ID,S.STU_NAME, SUM(G.SCORE) AS TOTALSCORE " +
            "FROM GRADE G, STUDENT S " +
            "WHERE G.STU_ID=S.STU_ID AND S.STU_ID=#{stuId}")
    List<Map<String, Object>> findTotalScoreByStuId(@Param("stuId") String stuId);

    @Select("SELECT CL.CLASS_ID, CL.CLASS_NAME,  C.COUR_NAME, ROUND(AVG(G.SCORE),1) AS AVGSCORE " +
            "FROM GRADE G, STUDENT S, COURSE C, CLASS CL" +
            "WHERE G.STU_ID=S.STU_ID AND G.COUR_ID=C.COUR_ID AND S.STU_CLASS_ID=CL.CLASS_ID" +
            "GROUP BY CL.CLASS_ID, C.COUR_NAME")
    List<Map<String, Object>> singleSubjectAvgScoreOfClasses();

    @Select("SELECT S.STU_CLASS_ID, ROUND(SUM(G.SCORE)/COUNT( DISTINCT S.STU_ID) ,1) AS AVGSCORE" +
            "FROM GRADE G, STUDENT S" +
            "WHERE G.STU_ID=S.STU_ID" +
            "GROUP BY S.STU_CLASS_ID")
    List<Map<String, Object>> avgScoreOfClasses();

    @Update("UPDATE GRADE SET SCORE=#{grade.score} " +
            "WHERE STU_ID=#{grade.stuId} AND " +
            "COUR_ID=#{grade.courId}")
    int updateGrade(@Param("grade") Grade grade);
}
