package com.rabbit.direct;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 刘振河
 * @create 2020--11--23 17:51
 */
public class Consume2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String queue = channel.queueDeclare().getQueue();
        // 添加多绑定
        channel.queueBind(queue,"logs_direct","waring");
        channel.queueBind(queue,"logs_direct","info");
        channel.queueBind(queue,"logs_direct","error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2"+new String(body));
            }
        });
    }
}
