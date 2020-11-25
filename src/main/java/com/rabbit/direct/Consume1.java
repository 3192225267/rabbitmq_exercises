package com.rabbit.direct;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 刘振河
 * @create 2020--11--23 17:43
 */
public class Consume1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel = connection.createChannel();
        // 声明交换机以及交换机类型
        channel.exchangeDeclare("logs_direct","direct");
        // 创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        // 交换机绑定临时队列并定义路由规则
        // 参数1：队列名称，参数2：交换机名称，参数3：Routeing Key
        channel.queueBind(queue,"logs_direct","error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1"+new String(body));
            }
        });
    }
}
