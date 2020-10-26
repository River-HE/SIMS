package tech.yangxm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.pojo.Grade;
import tech.yangxm.sims.service.GradeService;

import java.util.List;
import java.util.Map;

@RestController
public class GradeController {

    @Autowired
    private GradeService gs;

    @GetMapping("/grade")
    public List<Map<String, Object>> findGradesByStuId(@RequestParam("stuId") String stuId) {
        return gs.findGradesByStuId(stuId);
    }

    @GetMapping("/total_score")
    public List<Map<String, Object>> findTotalScoreByStuId(@RequestParam("stuId") String stuId) {
        return gs.findTotalScoreByStuId(stuId);
    }

    @GetMapping("/single_subject_avg_score")
    public List<Map<String, Object>> singleSubjectAvgScoreOfClasses() {
        return gs.singleSubjectAvgScoreOfClasses();
    }

    @GetMapping("/classes_avg_score")
    public List<Map<String, Object>> avgScoreOfClasses() {
        return gs.avgScoreOfClasses();
    }

    @PostMapping("/record_grade")
    public int recordGrade(@RequestBody Grade grade) {
        return gs.recordGrade(grade);
    }
}
