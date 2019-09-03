package cn.xiaobao.community;

import cn.xiaobao.community.entity.User;
import cn.xiaobao.community.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void getConnection(){

    }
    @Test
    public void contextLoads() {
    }

}
