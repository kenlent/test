/*******************************************************************************
 * 广州理德物联网科技有限公司
 * Copyright (c) 2017.
 ******************************************************************************/

package com.vsthk.api.app.druid.filter;

/**
 * Created by tanqimin on 17-3-2.
 */

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.woff,*.woff2,*.ttf,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {

}