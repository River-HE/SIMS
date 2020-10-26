package tech.yangxm.sims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.yangxm.sims.mapper.StuClassMapper;
import tech.yangxm.sims.pojo.StuClass;
import tech.yangxm.sims.service.StuClassService;

import java.util.List;

@Service("StuClassService")
public class StuClassServiceImpl implements StuClassService {

    @Autowired
    private StuClassMapper scm;

    @Override
    public StuClass findById(String id) {
        return scm.selectById(id);
    }

    @Override
    public List<StuClass> findAll() {
        return scm.selectList(new QueryWrapper<>());
    }

    @Override
    public StuClass findByName(String name) {
        return scm.selectOne(new QueryWrapper<StuClass>().like("class_name", name));
    }

    @Override
    public int addClass(StuClass sc) {
        return scm.insert(sc);
    }

    @Override
    public int deleteClassById(String id) {
        return scm.deleteById(id);
    }

    @Override
    public int updateClassById(StuClass sc) {
        return scm.updateById(sc);
    }
}
