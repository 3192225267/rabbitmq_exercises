package com.rabbit.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 刘振河
 * @create 2020--11--21 14:35
 */
public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.57.190.18");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
    }

    public static Connection getConnectionFactory() {
        try {
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (Exception e) {

        }
        return null;
    }

    public static void closeConnectionAnChannel(Channel channel, Connection connection) throws IOException, TimeoutException {
        if (channel != null) {
            channel.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
