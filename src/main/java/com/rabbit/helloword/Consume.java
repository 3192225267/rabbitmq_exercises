package com.rabbit.helloword;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--21 13:06
 */
public class Consume {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel =connection.createChannel();
        // 通道绑定对象
        channel.queueDeclare("hello",true,false,true,null);
        // 参数 1：消费哪个队列的消息 队列名称
        // 参数 2：开始消息的自动确认机制
        // 参数 3：消费是时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body) {
                System.out.println("new String(body)="+new String(body));
            }
        });
    }
}
