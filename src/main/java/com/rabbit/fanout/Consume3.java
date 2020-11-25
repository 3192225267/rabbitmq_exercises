package com.rabbit.fanout;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 刘振河
 * @create 2020--11--23 15:13
 */
public class Consume3 {
    public static void main(String[] args) throws IOException {
        // 创建连接
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel = connection.createChannel();
        // 通道绑定交换机
        channel.exchangeDeclare("logs","fanout");
        // 创建一个临时队列
        String queuName = channel.queueDeclare().getQueue();
        // 将队列与交换机绑定
        channel.queueBind(queuName,"logs","");

        // 消费信息
        channel.basicConsume(queuName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3="+new String(body));
            }
        });

    }
}
