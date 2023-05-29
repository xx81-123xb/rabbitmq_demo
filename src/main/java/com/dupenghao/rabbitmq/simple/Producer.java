package com.dupenghao.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by 杜鹏豪 on 2023/5/29.
 */
public class Producer {
    public static void main(String[] args) {
        String ip = "192.168.1.83";
        int port = 5672;
        String username = "admin";
        String password = "admin";
        String virtualHost = "/";
        String queueName = "queue1";
        String message = "nizhidao woyou duo xiangni me";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);

        Connection connection = null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println("Send message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
