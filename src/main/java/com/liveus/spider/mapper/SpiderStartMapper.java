package com.liveus.spider.mapper;

import com.liveus.spider.pojo.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/9/23 16:23
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Component
@Mapper
public interface SpiderStartMapper {

    void insertCSDN(Blog blog);

    Blog selectCSDNByTitle(@Param("title") String title);

    void updateCSDNByTitle(Blog blog);

    void insertJuejin(Blog blog);

    Blog selectJuejinByTitle(@Param("title") String title);

    void updateJuejinByTitle(Blog blog);

}
