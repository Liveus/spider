package com.liveus.spider.controller;

import com.liveus.spider.pojo.dto.SpiderDto;
import com.liveus.spider.service.ISpiderStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/23 16:10
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Controller
public class SpiderStartController  {

    @Autowired
    private ISpiderStartService iSpiderStartService;

    @GetMapping("/csdn")
    @ResponseBody
    public String csdnSpider(@ModelAttribute SpiderDto dto){
        return this.iSpiderStartService.csdnSpider(dto);
    }

    @GetMapping("/juejin")
    @ResponseBody
    public String juejinSpider(@ModelAttribute SpiderDto dto){
        return this.iSpiderStartService.juejinSpider(dto);
    }

}
