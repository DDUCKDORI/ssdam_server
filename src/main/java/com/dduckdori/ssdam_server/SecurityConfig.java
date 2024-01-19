package com.dduckdori.ssdam_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
//                .requiresChannel(channel->channel.anyRequest().requiresSecure())
                .authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
               ;
        return httpSecurity.build();
    }
}
