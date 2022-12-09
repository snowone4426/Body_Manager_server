package net.ict.bodymanager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter(){
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); //내 서버의 json 응답을 js 가 처리할 수 있게 설정
    config.addAllowedOrigin("http://localhost:3000"); //해당 ip에 응답을 허용
    config.addAllowedHeader("*"); //모든 header에 응답을 허용
    config.addAllowedMethod("*"); //모든 post, get, put, delete, patch 요청 허용
    config.setMaxAge(60*60L);
    config.addExposedHeader("Authorization");
    source.registerCorsConfiguration("/**",config);
    return new CorsFilter(source);
  }


}
