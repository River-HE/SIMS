package tech.yangxm.sims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.GradeMapper;
import tech.yangxm.sims.pojo.Grade;
import tech.yangxm.sims.service.GradeService;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("GradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gm;

    @Override
    public List<Map<String, Object>> findGradesByStuId(String stuId) {
        return gm.findGradesByStuId(stuId);
    }

    @Override
    public List<Map<String, Object>> findTotalScoreByStuId(String stuId) {
        return gm.findTotalScoreByStuId(stuId);
    }

    @Override
    public List<Map<String, Object>> singleSubjectAvgScoreOfClasses() {
        return gm.singleSubjectAvgScoreOfClasses();
    }

    @Override
    public List<Map<String, Object>> avgScoreOfClasses() {
        return gm.avgScoreOfClasses();
    }

    @Override
    public int recordGrade(Grade grade) {
        Grade g = gm.selectOne(new QueryWrapper<Grade>().apply
                ("STU_ID={0} AND COUR_ID={1}", grade.getStuId(),
                        grade.getCourId()));
        if (g == null) {
//            log.info("g is null");
            return gm.insert(grade);
        } else {
//            log.info("g is not null");
            return gm.updateGrade(grade);
        }
    }
}
