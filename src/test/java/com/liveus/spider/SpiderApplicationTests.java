package com.liveus.spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Test
    public void contextLoads() {
/*        String time = "2016年10月09日 16:38:27";
        time = time.replace("年","-").replace("月","-").replace("日","");
        System.out.println(time);
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(time,dateTimeFormatter);

        System.out.println(localDateTime);*/
        String a = "https://blog.csdn.net/LoveRestart/article/details/71749183";
        String regex = "https://blog\\.csdn\\.net/LoveRestart/article/details/\\d+";
        System.out.println(a);
        a = a.substring(0,a.lastIndexOf("/")).replace(".","\\.")+"/\\d+";
        System.out.println(a);
        System.out.println(regex);
    }

/*    tags:	[]
    time:	2016年10月09日 16:38:27
    type:	 Java Web
    author:	理强
    good:	1
    comment:	 1
    comments:
    read:	阅读数 987*/
}
