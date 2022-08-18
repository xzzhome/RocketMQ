package sync;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        message.setTopic("syn-topic");//消息主题
        message.setTags("sms");//消息标签
        message.setBody(("我是消息").getBytes(StandardCharsets.UTF_8));//添加内容

        //延迟级别 3，代表 10s延迟
        message.setDelayTimeLevel(3);

        //执行发送
        SendResult result = producer.send(message);
        //打印结果
        System.out.println(result);
        System.out.println("发送时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        producer.shutdown();
    }
}
