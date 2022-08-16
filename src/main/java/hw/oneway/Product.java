package hw.oneway;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

public class Product {

    public static void main(String[] args) throws Exception {
        //生产者
        DefaultMQProducer producer = new DefaultMQProducer("oneway-producerGroup");
        //设置name server地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //启动
        producer.start();
        //创建消息信息,执行发送
        producer.sendOneway(new Message("oneway-topic","sms",("我是单向消息").getBytes(StandardCharsets.UTF_8)));
        producer.shutdown();
    }
}
