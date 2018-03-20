package com.changfubai.springboot.security.oauth.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import javax.sql.DataSource;


/**
 * Created by changfubai on 2018/3/19
 */
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置自定义的登陆页面，不配置会跳转到官方默认login，
        //就算重新指定login映射，security也不会接管我们的oauth2.0验证
        http.oauth2Login()
                .loginPage("/userLogin");
                    //.redirectionEndpoint().baseUri("/login/oauth2/callback/*");

        //配置开放页面index 和 home  放行静态资源/css 以及登陆页面和登出页面
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/userLogin").permitAll().and().logout();

        //配置错误时访问页面
        http.exceptionHandling().accessDeniedPage("/403");
    }


    /**
     * 使用除github、google、facebook、OKta以外的第三方需要自定义
     * 有两种方式：</br>
     * ** 在继承了WebSecurityConfigurerAdapter 的配置类中定义Bean 以及返回对应的第三方配置，如下
     *
     * ** 在application 文件中定义
     */
    //@Bean
    //public ClientRegistrationRepository clientRegistrationRepository() {
    //    return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    //}
    //
    //private ClientRegistration googleClientRegistration() {
    //
    //    Logger logger = LogManager.getLogger(OAuth2LoginConfig.class);
    //    logger.info("执行自定义的注册服务供应商信息------------");
    //    return ClientRegistration.withRegistrationId("google")
    //            .clientId("575072657906-deuevn1bftngal2g8n88k6ffrf1g0og6.apps.googleusercontent.com")
    //            .clientSecret("LmnvtKNxYeTDzosyONmeeSTi")
    //            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
    //            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
    //            .scope("openid", "profile", "email", "address", "phone")
    //            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
    //            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
    //            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
    //            .userNameAttributeName(IdTokenClaimNames.SUB)
    //            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
    //            .clientName("Google")
    //            .build();
    //}
}
