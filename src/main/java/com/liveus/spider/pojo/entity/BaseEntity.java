package com.liveus.spider.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/24 10:14
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "创建人",hidden = true)
    private Integer createBy;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人",hidden = true)
    private Integer modifyBy;

    @ApiModelProperty(value = "修改时间",hidden = true)
    private LocalDateTime modifyTime;

}
