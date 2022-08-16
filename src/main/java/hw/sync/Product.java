package hw.sync;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

public class Product {

    public static void main(String[] args) throws Exception {
        //生产者
        DefaultMQProducer producer = new DefaultMQProducer("syn-producerGroup");

        //设置name server地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        //启动
        producer.start();

        //创建消息信息
        Message message = new Message();
        producer.sendOneway(new Message("syn-topic","sms",("我是同步消息").getBytes(StandardCharsets.UTF_8)));

        //执行发送
        SendResult result = producer.send(message);
        //打印结果
        System.out.println(result);

        producer.shutdown();
    }
}
