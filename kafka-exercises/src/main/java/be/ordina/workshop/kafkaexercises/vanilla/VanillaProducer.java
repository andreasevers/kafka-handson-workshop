package be.ordina.workshop.kafkaexercises.vanilla;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by ToVn on 02/10/17.
 */
public class VanillaProducer {

    private final Properties props;


    public VanillaProducer() {

        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


    }

    public List<Future> sendMessages(final String topic, final List<String> messages) {

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        List<Future> futures = new ArrayList<>();

        for (String message : messages) {
            futures.add(producer.send(new ProducerRecord<>(topic, message)));
        }

        producer.close();

        return futures;
    }
}