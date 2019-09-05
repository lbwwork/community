package cn.xiaobao.community.mappers;

import cn.xiaobao.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 添加一个用户
     */
    Integer insert(User user);

    /**
     * 根据token查询用户
     * @return
     */
    User findByToken(String token);
}
