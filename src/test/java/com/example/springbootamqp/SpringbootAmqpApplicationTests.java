package com.example.springbootamqp;

import com.example.springbootamqp.Bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//        System.out.println("创建完成");
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
    }

    /**
     * 1.单播，点对点
     */
    @Test
    public void contextLoads() {
        //Message 需要自己构造一个，定义消息体内容和消息头
        //rabbitTemplate.send();
        //需要传入要发送的对象，自动序列化，发送给rabbitmq
        Map<String,Object> map =new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
        //对象被默认序列化后发送
        Book book =new Book("三国演义","罗贯中");
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",book);
    }
    //接收数据
    @Test
    public void receive(){
//        String o = rabbitTemplate.receiveAndConvert("atguigu.news").toString();
        Object o = rabbitTemplate.receive("atguigu.news");
        System.out.println(o.getClass());

        System.out.println(o);
    }
    //广播
    public void sendMsg(){

    }
}
