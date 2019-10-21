package cn.xiaobao.community.mappers;

import cn.xiaobao.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuesstionMapper {

    public void create(Question question);
}
