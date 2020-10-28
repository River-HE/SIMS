package tech.yangxm.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.yangxm.sims.pojo.User;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {


}
