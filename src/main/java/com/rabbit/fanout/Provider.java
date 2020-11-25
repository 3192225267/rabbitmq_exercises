package com.rabbit.fanout;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--23 15:12
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接对象
        Connection connection= RabbitMQUtils.getConnectionFactory();
        Channel channel=connection.createChannel();
        // 为通道指定交换机，
        // 参数1：交换机名称
        // 参数2： 交换机类型
        channel.exchangeDeclare("logs","fanout");
        // 发送消息
        channel.basicPublish("logs","",null,("fanout Type Message").getBytes());
        RabbitMQUtils.closeConnectionAnChannel(channel,connection);
    }

}
