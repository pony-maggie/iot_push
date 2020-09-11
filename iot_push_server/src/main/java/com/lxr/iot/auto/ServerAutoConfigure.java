package com.lxr.iot.auto;

import com.lxr.iot.enums.ProtocolEnum;
import com.lxr.iot.properties.InitBean;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动化配置初始化服务
 *
 * @author lxr
 * @create 2017-11-29 19:52
 *
 * @EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解的类生效。
 **/
@Configuration
@ConditionalOnClass
@EnableConfigurationProperties({InitBean.class})
public class ServerAutoConfigure {


    private static  final  int _BLACKLOG =   1024;

    private static final  int  CPU =Runtime.getRuntime().availableProcessors();

    private static final  int  SEDU_DAY =10;

    private static final  int TIMEOUT =120;

    private static final  int BUF_SIZE=10*1024*1024;


    public ServerAutoConfigure(){

    }


    /**
     * iot_push_server_starter_test工程启动，会配置ServerAutoConfigure，然后InitServer这个bean创建完成后，
     * open方法会被调用。启动服务器
     * @param serverBean
     * @return
     */

    @Bean(initMethod = "open", destroyMethod = "close")
    @ConditionalOnMissingBean
    public InitServer initServer(InitBean serverBean){
        if(!ObjectUtils.allNotNull(serverBean.getPort(),serverBean.getServerName())){
            throw  new NullPointerException("not set port");
        }
        if(serverBean.getBacklog()<1){
            serverBean.setBacklog(_BLACKLOG);
        }
        if(serverBean.getBossThread()<1){
            serverBean.setBossThread(CPU);
        }
        if(serverBean.getInitalDelay()<0){
            serverBean.setInitalDelay(SEDU_DAY);
        }
        if(serverBean.getPeriod()<1){
            serverBean.setPeriod(SEDU_DAY);
        }
        if(serverBean.getHeart()<1){
            serverBean.setHeart(TIMEOUT);
        }
        if(serverBean.getRevbuf()<1){
            serverBean.setRevbuf(BUF_SIZE);
        }
        if(serverBean.getWorkThread()<1){
            serverBean.setWorkThread(CPU*2);
        }
        if(serverBean.getProtocol()==null){
            serverBean.setProtocol(ProtocolEnum.MQTT);
        }
        return new InitServer(serverBean);
    }


}
