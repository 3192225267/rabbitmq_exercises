package com.rabbit.direct;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--23 17:21
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel = connection.createChannel();
        String exchangeName="logs_direct";
        // 绑定交换机 参数1：交换机名称，参数2：交换机类型，基于Routeing Key转发
        channel.exchangeDeclare(exchangeName,"direct");
        String routeingKey="info";
        // 发布消息
        channel.basicPublish(exchangeName,routeingKey,null,("指定的Routeing key"+routeingKey+"发送的消息").getBytes());
        RabbitMQUtils.closeConnectionAnChannel(channel,connection);
    }
}
