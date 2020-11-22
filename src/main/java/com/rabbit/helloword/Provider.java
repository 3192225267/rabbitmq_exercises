package com.rabbit.helloword;

import com.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--16 16:32
 */
public class Provider {
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        //获取连接中通道
        Channel channel =connection.createChannel();
        // 通道绑定对应消息队列
        // 参数1：队列名称，如果队列不存在自动创建
        // 参数2：用来定义队列特性是否要持久化 true 持久化队列，false 不持久化队列
        // 参数3：exclusive 是否独占队列，true独占队列，false 不独占
        // 参数 4. autoDelete:是否在消费完成后自动删除队列 true 自动删除 ，false 不自动删除
        // 参数5：额外附加参数
        channel.queueDeclare("hello",true,false,true,null);
        //发布信息
        // 参数1：交换机名称，参数二 队列名称，参数三：传递额外消息设置 参数4：消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());
        channel.close();
        connection.close();
    }
}
