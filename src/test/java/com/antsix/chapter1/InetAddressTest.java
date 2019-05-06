package com.antsix.chapter1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class  InetAddressTest{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        //String sql = "SELECT COUNT(*) FROM REPORT_RECORD_BY_LOCAL";
        //int num = jdbcTemplate.queryForObject(sql,Integer.class);
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("```````````IP : "+ip);
            System.out.println("```````````getLoopbackAddress : "+InetAddress.getLoopbackAddress().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
