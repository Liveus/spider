package com.liveus.spider.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/23 16:12
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
public class SpiderDto {

    @ApiModelProperty("爬虫开始url")
    private String url;

    @ApiModelProperty("url过滤")
    private String regex;
}
