package com.liveus.spider.service.Impl;

import com.liveus.spider.pojo.dto.SpiderDto;
import com.liveus.spider.service.ISpiderStartService;
import com.liveus.spider.utils.pageProcessor.CsdnPageProcessor;
import com.liveus.spider.utils.pageProcessor.JuejinPageProcessor;
import com.liveus.spider.utils.pipeline.CSDNPipeline;
import com.liveus.spider.utils.pipeline.JuejinPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;


/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/23 16:16
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Service
@Transactional
public class SpiderStartServiceImpl implements ISpiderStartService {

    @Autowired
    CsdnPageProcessor csdnPageProcessor;

    @Autowired
    JuejinPageProcessor juejinPageProcessor;

    @Autowired
    CSDNPipeline csdnPipeline;

    @Autowired
    JuejinPipeline juejinPipeline;

    public String csdnSpider(SpiderDto dto){
        try {
            //爬虫配置
            Spider spider = Spider.create(csdnPageProcessor)
                    //开始url
                    .addUrl(dto.getUrl())
                    //输出形式
                    .addPipeline(csdnPipeline)
                    //线程数
                    .thread(5);
            //添加监控s
/*            SpiderMonitor.instance().register(spider);*/
            //启动
            spider.run();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @Override
    public String juejinSpider(SpiderDto dto) {
        try {
            //爬虫配置
            Spider spider = Spider.create(juejinPageProcessor)
                    //开始url
                    .addUrl(dto.getUrl())
                    //输出形式
                    .addPipeline(juejinPipeline)
                    //线程数
                    .thread(5);
            //添加监控s
            /*            SpiderMonitor.instance().register(spider);*/
            //启动
            spider.run();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

}
