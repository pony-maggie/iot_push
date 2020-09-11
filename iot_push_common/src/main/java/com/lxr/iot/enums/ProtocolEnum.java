package com.lxr.iot.enums;


/**
 * 协议
 *
 * @author lxr
 * @create 2018-01-06 15:13
 **/
public enum  ProtocolEnum {

    MQTT,

    /**
     * 应该是mqtt websocket
     */
    MQTT_WS_MQTT,

    /**
     * paho是实现MQTT通信的多平台多语言的库，支持Python，c++，PHP等，官网地址：
     *
     * https://www.eclipse.org/paho/
     *
     * 一般情况下，会使用mosquitto搭建服务器，客户端会使用paho库实现，
     */
    MQTT_WS_PAHO,

}
