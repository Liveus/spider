package com.liveus.spider.utils.pipeline;

import com.liveus.spider.mapper.SpiderStartMapper;
import com.liveus.spider.pojo.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

/**
 * @Desc: csdn
 * @author: Lenovo
 * @Time: 2019/9/25 10:06
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Component
public class CSDNPipeline implements Pipeline {

    @Autowired
    SpiderStartMapper spiderStartMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        Iterator var3 = resultItems.getAll().entrySet().iterator();
        Blog blog = new Blog();
        Blog data = null;
        while (var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var3.next();
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
            if(entry.getKey().equals("title")){
                blog.setTitle((String)entry.getValue());
            }
            switch (entry.getKey()) {
                case "title":
                    blog.setTitle((String)entry.getValue());
                    data = spiderStartMapper.selectCSDNByTitle((String)entry.getValue());
                    break;
                case "content":
                    blog.setContent((String)entry.getValue());
                    break;
                case "tags":
                    blog.setTags(entry.getValue().toString());
                    break;
                case "time":
                    blog.setTime((LocalDateTime)entry.getValue());
                    break;
                case "type":
                    blog.setType((String)entry.getValue());
                    break;
                case "author":
                    blog.setAuthor((String)entry.getValue());
                    break;
                case "good":
                    blog.setGoodSum((Integer)entry.getValue());
                    break;
                case "comment":
                    blog.setCommentSum((Integer)entry.getValue());
                    break;
                case "read":
                    blog.setReadSum((Integer)entry.getValue());
                    break;
            }
        }
        if (data == null) {//未存在相同blog则插入
            spiderStartMapper.insertCSDN(blog);
        } else if (
            //存在修改时间，如果修改时间不为今天则进行修改
                (data.getModifyTime() != null && (data.getModifyTime().getDayOfYear() < LocalDate.now().getDayOfYear())) ||
                        //不存在修改时间，如果创建时间不是今天则进行修改
                        (data.getModifyTime() == null && data.getCreateTime() != null && (data.getCreateTime().getDayOfYear() < LocalDate.now().getDayOfYear()))) {//已存在同名blog则更新
            //时间差5小时内则不更新
            spiderStartMapper.updateCSDNByTitle(blog);
        } else {
            //其余情况不处理
        }
    }
}
