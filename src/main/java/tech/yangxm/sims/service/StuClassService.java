package tech.yangxm.sims.service;

import tech.yangxm.sims.pojo.StuClass;

import java.util.List;

public interface StuClassService {

    StuClass findById(String id);

    List<StuClass> findAll();

    StuClass findByName(String name);

    int addClass(StuClass sc);

    int deleteClassById(String id);

    int updateClassById(StuClass sc);
}
