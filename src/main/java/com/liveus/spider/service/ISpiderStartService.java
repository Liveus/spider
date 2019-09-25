package com.liveus.spider.service;

import com.liveus.spider.pojo.dto.SpiderDto;

public interface ISpiderStartService {
    String csdnSpider(SpiderDto dto);

    String juejinSpider(SpiderDto dto);
}
