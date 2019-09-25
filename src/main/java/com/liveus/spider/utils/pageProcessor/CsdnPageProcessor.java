package com.liveus.spider.utils.pageProcessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Desc: csdn
 * @author: Lenovo
 * @Time: 2019/9/25 10:04
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Component
public class CsdnPageProcessor implements PageProcessor {

    private Site site = Site.me()
            //domain,添加域名之后才可以添加cookie
            .setDomain("blog.csdn.net")
            .addCookie("Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac","6525*1*10_21052047110-1569380996981-711765!5744*1*LoveRestart!1788*1*PC_VC")
            .addCookie("uuid_tt_dd","10_21052047110-1569380996981-711765")
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
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        //链接规则
        String regex = page.getUrl().toString().substring(0,page.getUrl().toString().lastIndexOf("/")).replace(".","\\.")+"/\\d+";
        List<String> links = page.getHtml().links().regex(regex).all();
        page.addTargetRequests(links);
        //爬取元素
        String title = page.getHtml().xpath("//title/text()").toString();
        String content = page.getHtml().$("div.markdown_views").toString();
        List tags = page.getHtml().xpath("//span[@class='tags-box artic-tag-box']/a/text()").all();

        System.out.println(page.getHtml());
        System.out.println("ffff:"+page.getHtml().xpath("//span[@class='time']/text()").toString());

        String time = page.getHtml().xpath("//span[@class='time']/text()").toString().replace("年","-").replace("月","-").replace("日","");;

        String type = page.getHtml().xpath("//div[@class='tags-box space']/a/text()").toString();
        String author = page.getHtml().xpath("//div[@class='article-bar-top']/a/text()").toString();
        String goodStr = page.getHtml().xpath("//p[@id='supportCount']/text()").toString().replace(" ","");
        String CommentStr = page.getHtml().xpath("//a[@class='btn-comments long-height hover-box']/p/text()").toString();

        if(CommentStr==null){//评论数可能会出现null
            CommentStr = "";
        }else{
            CommentStr = CommentStr.replace(" ","");
        }
        String readStr = page.getHtml().xpath("//div[@class='article-bar-top']/span[@class='read-count']/text()").toString().replace("阅读数 ","").replace(" ","");

        Integer goodSum = 0;
        if(!goodStr.equals("")){
            goodSum = Integer.valueOf(goodStr);
        }
        Integer commentSum = 0;
        if(!CommentStr.equals("")){
            commentSum = Integer.valueOf(CommentStr);
        }
        Integer readSum = 0;
        if(!readStr.equals("")){
            readSum = Integer.valueOf(readStr);
        }

        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        page.putField("title", title);
        page.putField("content", content);
        page.putField("tags",tags);
        page.putField("time", LocalDateTime.parse(time,dateTimeFormatter));
        page.putField("type",type);
        page.putField("author",author);
        page.putField("good",goodSum);
        page.putField("comment",commentSum);
        page.putField("read",readSum);
    }

}
