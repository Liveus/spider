package com.liveus.spider.utils.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Iterator;
import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/24 16:17
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Component
public class MyPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        Iterator var3 = resultItems.getAll().entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var3.next();
            System.out.println((String)entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
