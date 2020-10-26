package tech.yangxm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.yangxm.sims.pojo.StuClass;
import tech.yangxm.sims.service.StuClassService;

import java.util.List;

@RestController
public class StuClassController {

    @Autowired
    private StuClassService cs;

    @GetMapping("/class")
    public StuClass findById(@RequestParam("id") String id) {
        return cs.findById(id);
    }

    @GetMapping("/classes")
    public List<StuClass> findAll() {
        return cs.findAll();
    }

    @GetMapping("/class/name")
    public StuClass findByName(@RequestParam("name") String name) {
        return cs.findByName(name);
    }

    @PostMapping("/class")
    public int addClass(@RequestBody StuClass sc) {
        return cs.addClass(sc);
    }

    @DeleteMapping("/class")
    public int deleteClassById(@RequestParam("id") String id) {
        return cs.deleteClassById(id);
    }

    @PutMapping("/class")
    public int updateClassById(@RequestBody StuClass sc) {
        return cs.updateClassById(sc);
    }
}
