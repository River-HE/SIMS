package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {

    List<Map<String, Object>> findGradesByStuId(String stuId);

    List<Map<String, Object>> findTotalScoreByStuId(String stuId);

    List<Map<String, Object>> singleSubjectAvgScoreOfClasses();

    List<Map<String, Object>> avgScoreOfClasses();

    int recordGrade(Grade grade);
}
