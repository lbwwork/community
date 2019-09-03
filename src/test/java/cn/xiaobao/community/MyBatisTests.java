package cn.xiaobao.community;

import cn.xiaobao.community.entity.User;
import cn.xiaobao.community.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommunityApplication.class)
public class MyBatisTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public  void insert(){
        try {
            System.out.println(userMapper);
            User user = new User();
            user.setName("test");
            user.setAccountId("1");
            userMapper.insert(user);
            System.out.println("1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
