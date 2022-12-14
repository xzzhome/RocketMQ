package hw.sync;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws Exception {
        //消费者
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("syn-consumerGroup");

        //设置地址
        defaultMQPushConsumer.setNamesrvAddr("127.0.0.1:9876");

        //订阅消息
        defaultMQPushConsumer.subscribe("syn-topic","*");

        //监听消息
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
              @Override
              public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                  for (MessageExt ext : list) {
                      System.out.println(new String(ext.getBody(),StandardCharsets.UTF_8));
                  }
                  return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
              }
          }
        );
        defaultMQPushConsumer.start();
    }
}
