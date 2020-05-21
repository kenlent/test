
package com.vsthk.api.app;


import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

  private final static long UPDATE_FILE_SIZE = 1024L * 1024L * 50;

  @Autowired
  private MultipartProperties multipartProperties;

//  @Autowired
//  private SecurityInterceptor securityInterceptor;
//
//  @Autowired
//  private RequestLoggerInterceptor requestLoggerInterceptor;
//
//  @Autowired
//  private DocsPermissionInterceptor docsPermissionInterceptor;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/META-INF/resources/")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/public/")
        .addResourceLocations("classpath:/html/");
    super.addResourceHandlers(registry);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:/doc/index.html");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    super.addViewControllers(registry);
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer
        .defaultContentType(MediaType.APPLICATION_JSON)
        .favorPathExtension(true);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //converters.add(fastJsonHttpMessageConverters());
    //super.configureMessageConverters(converters);
    StringHttpMessageConverter converter=new StringHttpMessageConverter(Charset.forName("UTF-8"));
    converters.add(converter);
  }

//  /**
//   * 注入拦截器
//   */
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry
//        .addInterceptor(securityInterceptor)
//        .addPathPatterns("/api/**");
//    registry.addInterceptor(requestLoggerInterceptor).addPathPatterns("/api/**");
//    registry.addInterceptor(docsPermissionInterceptor)
//        .excludePathPatterns("/doc/login.html")
//        .addPathPatterns("/doc/**");
//  }



  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedHeaders("*")
        .allowedMethods("*")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .maxAge(3600);
  }

  @Bean
  public MultipartFilter multipartFilter() {
    MultipartFilter multipartFilter = new MultipartFilter();
    multipartFilter.setMultipartResolverBeanName("multipartReso‌‌​​lver");
    return multipartFilter;
  }

  @Bean
  @ConditionalOnMissingBean
  public MultipartConfigElement multipartConfigElement() {
    return this.multipartProperties.createMultipartConfig();
  }

//  @Bean
//  public CommonsMultipartResolver multipartReso‌‌​​lver() {
//    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//    commonsMultipartResolver.setDefaultEncoding("UTF-8");
//    commonsMultipartResolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//    commonsMultipartResolver.setMaxInMemorySize(40960);
//    commonsMultipartResolver.setMaxUploadSize(UPDATE_FILE_SIZE);
//    return commonsMultipartResolver;
//  }


  /**
   * 修改默认的JSON实现为FastJson
   */
//  @Bean
//  public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
//    FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
//    httpMessageConverter.setFastJsonConfig(getFastJsonConfig());
//    httpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
//    return httpMessageConverter;
//  }
//
  private FastJsonConfig getFastJsonConfig() {
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN);
    fastJsonConfig.setCharset(Charset.forName("UTF-8"));
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

    fastJsonConfig.setSerializerFeatures(
        SerializerFeature.WriteMapNullValue,
        SerializerFeature.QuoteFieldNames,
        SerializerFeature.WriteNullListAsEmpty,
        SerializerFeature.WriteEnumUsingToString,
        SerializerFeature.WriteDateUseDateFormat);
    return fastJsonConfig;
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }
}
