package com.liveus.spider.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/23 16:12
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog extends BaseEntity{
    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("创作时间")
    private LocalDateTime time;

    @ApiModelProperty("文章类别")
    private String type;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("点赞数")
    private Integer goodSum;

    @ApiModelProperty("评论数")
    private Integer commentSum;

    @ApiModelProperty("阅读数")
    private Integer readSum;
}
