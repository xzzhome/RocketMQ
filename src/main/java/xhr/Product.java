package xhr;

import io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

public class Product {

    public static void main(String[] args) throws Exception {
        //生产者
        DefaultMQProducer producer = new DefaultMQProducer("xhr-producerGroup");

        //设置name server地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        //启动
        producer.start();

        //创建并且发送消息信息
        producer.send(
                //创建消息对象
                new Message("xhr-topic", "sms", "我是异步消息".getBytes(CharsetUtil.UTF_8)),
                //添加发送回调
                new SendCallback() {

                    //发送成功结果处理
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }
                    //发送异常结果处理
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("异常信息");
                    }
                }
        );

    }
}
