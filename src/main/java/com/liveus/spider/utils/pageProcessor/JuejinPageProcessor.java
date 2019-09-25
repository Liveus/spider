package com.liveus.spider.utils.pageProcessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Desc: juejin
 * @author: Lenovo
 * @Time: 2019/9/25 13:39
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Component
public class JuejinPageProcessor implements PageProcessor {

    private Site site = Site.me()
            //domain,添加域名之后才可以添加cookie
//            .setDomain("juejin.im")
//            .addCookie("Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac","6525*1*10_21052047110-1569380996981-711765!5744*1*LoveRestart!1788*1*PC_VC")
//            .addCookie("uuid_tt_dd","10_21052047110-1569380996981-711765")
/*          .addCookie()
            .addCookie()
            .addCookie()
            .addCookie()
            .addCookie()*/
            //重试次数
            .setRetryTimes(2)
            //抓取间隔
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        //链接规则
        String regex = page.getUrl().toString().substring(0,page.getUrl().toString().lastIndexOf("/")).replace(".","\\.")+"/\\w+";

        regex = "/post/\\w+";

        List<String> links = page.getHtml().links().regex(regex).all();

        page.addTargetRequests(links);
        //爬取元素
        String title = page.getHtml().xpath("//title/text()").toString();
        String content = page.getHtml().$("div.article-content").toString();
        String time = page.getHtml().xpath("//time[@class='time']/text()").toString().replace("年","-").replace("月","-").replace("日","");;
        String author = page.getHtml().xpath("//a[@class='username username ellipsis']/text()").toString();
        System.out.println(page.getHtml().xpath("//div[@class='author-info-box']/text()"));
        String readStr = null;
        try {
            readStr = page.getHtml().xpath("///span[@class='views-count']/text()").toString().replace("阅读 ","").replace(" ","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int readSum = 0;
        if(readStr!=null&&!readStr.equals("")){
            readSum = Integer.parseInt(readStr);
        }
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        page.putField("title", title);
        page.putField("content", content);
        page.putField("time", LocalDateTime.parse(time+" 00:00:00",dateTimeFormatter));
        page.putField("author",author);
        page.putField("read",readSum);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
