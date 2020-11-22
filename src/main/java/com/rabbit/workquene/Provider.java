package com.rabbit.workquene;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--21 18:03
 */
public class Provider {
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel=connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);
        for (int i = 0; i <20 ; i++) {
            channel.basicPublish("","hello",null,(i+"WorkQuene").getBytes());
        }
        RabbitMQUtils.closeConnectionAnChannel(channel,connection);
    }
}
