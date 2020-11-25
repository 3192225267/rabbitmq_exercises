package com.rabbit.workquene;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 刘振河
 * @create 2020--11--21 18:02
 */
public class Consume1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        final Channel channel = connection.createChannel();
        //  每一次只能消费一条信息
        channel.basicQos(1);
        channel.queueDeclare("hello",false,false,false,null);
        // 关闭消息队列的自动确认消息机制（第二个参数）
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                }
                System.out.println("giao哥抢到了"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
