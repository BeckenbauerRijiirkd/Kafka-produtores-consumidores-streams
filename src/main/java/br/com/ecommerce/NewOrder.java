package br.com.ecommerce;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrder {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        var producer = new KafkaProducer<String,String>(properties());
        var orderDispatcher = new KafkaDispatcher<Order>();
        try {
            var emailDispatcher = new KafkaDispatcher<String>();
            for (int i = 0; i < 10; i++) {

                var userId = UUID.randomUUID().toString();
                var ordeId = UUID.randomUUID().toString();
                var amount = new BigDecimal(Math.random() * 5000 + 1);

                var order = new Order(userId, ordeId, amount);
                orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                var email = "teste@hotmail.com";

                emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);

            }
        } catch (InterruptedException e) {

        }
        ;
    }


}
