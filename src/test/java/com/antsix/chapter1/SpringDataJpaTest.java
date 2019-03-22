package com.antsix.chapter1;

import com.antsix.domain.entity.User;
import com.antsix.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringDataJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception {
        // 创建10条记录
        userRepository.save(new User("AAA", 10));
        userRepository.save(new User("BBB", 20));
        userRepository.save(new User("CCC", 30));
        userRepository.save(new User("DDD", 40));
        userRepository.save(new User("EEE", 50));
        userRepository.save(new User("FFF", 60));
        userRepository.save(new User("GGG", 70));
        userRepository.save(new User("HHH", 80));
        userRepository.save(new User("III", 90));
        userRepository.save(new User("JJJ", 100));

        System.out.println("userRepository.findAll().size() : "+userRepository.findAll().size());

        System.out.println("userRepository.findByName(\"FFF\").getAge() : " +userRepository.findByName("FFF").getAge());

        System.out.println("userRepository.findUser(\"FFF\").getAge() : "+userRepository.findUser("FFF").getAge());

        System.out.println("userRepository.findByNameAndAge(\"FFF\", 60) : "+userRepository.findByNameAndAge("FFF", 60));

        System.out.println("userRepository.delete(userRepository.findByName(\"AAA\"));");
        userRepository.delete(userRepository.findByName("AAA"));

        System.out.println("userRepository.findAll().size() : "+userRepository.findAll().size());
    }
}
