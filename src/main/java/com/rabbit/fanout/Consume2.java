package com.rabbit.fanout;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Collection;

/**
 * @author 刘振河
 * @create 2020--11--23 15:35
 */
public class Consume2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        Channel channel=connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("logs","fanout");
        // 创建一个临时的队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机和临时队列
        // 参数1:临时队列名称  参数2:
        // 参数2:交换机名称（要绑定的）
        // 参数3：路由规则
        channel.queueBind(queueName, "logs","");
        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2："+new String(body));
            }
        });
    }
}
