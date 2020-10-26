package tech.yangxm.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.yangxm.sims.pojo.StuClass;

@Mapper
@Repository
public interface StuClassMapper extends BaseMapper<StuClass> {

}
