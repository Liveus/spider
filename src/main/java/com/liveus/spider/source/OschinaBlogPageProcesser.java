package com.liveus.spider.source;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/21 10:09
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
public class OschinaBlogPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("my.oschina.net");

    @Override
    public void process(Page page) {
        System.out.println(page.getUrl().toString());
        //csdn
        List<String> links = page.getHtml().links().regex("https://blog\\.csdn\\.net/LoveRestart/article/details/\\d+").all();

        //oschina
        /* List<String> links = page.getHtml().links().regex("https://my\\.oschina\\.net/u/4215320/blog/\\d+").all();*/

        for (String link :links){
            System.out.println("link:"+link);
        }
        page.addTargetRequests(links);
        //csdn
        page.putField("title", page.getHtml().xpath("//title/text()").toString());
        page.putField("content", page.getHtml().$("div.markdown_views").toString());
        page.putField("tags",page.getHtml().xpath("//span[@class='tags-box artic-tag-box']/a/text()").all());

        //oschina
        /*page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());*/

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //csdn
        Spider.create(new OschinaBlogPageProcesser()).addUrl("https://blog.csdn.net/LoveRestart/article/details/71749183")
                .addPipeline(new ConsolePipeline()).run();
        //oschina
        Spider.create(new OschinaBlogPageProcesser()).addUrl("https://my.oschina.net/u/4215320/blog/3108015")
                .addPipeline(new ConsolePipeline()).run();

    }
}
