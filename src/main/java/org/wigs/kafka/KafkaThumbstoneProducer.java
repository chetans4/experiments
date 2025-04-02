package org.wigs.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.time.LocalTime;
import java.util.Properties;

public class KafkaThumbstoneProducer {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.10.11.111:9092"); // Kafka broker
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            // Ensure the topic is compacted in Kafka settings
            String topic = "workiva.alpha.schedule-service.v0.compaction-test";

            // Create a tombstone message (key with null value)
//            String key = "java-thumbs-" + LocalTime.now();

//            for (int i = 31; i <= 35; i++) {
//                String key = i+"";
//                String value = null;
//                sendMessage(topic, key, value, producer);
//            }

            for (int j = 0; j < 3; j++) {
                //Send same keys for 3 time to make dirty ratio > 0.01
                for (int i = 61; i <= 65; i++) {
                    String key = i+"";
                    String value = "value";
                    sendMessage(topic, key, value, producer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String topic, String key, String value, Producer<String, String> producer) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        producer.send(record, (RecordMetadata metadata, Exception exception) -> {
            if (exception == null) {
                System.out.printf("Tombstone message sent successfully to topic %s, partition %d, offset %d\n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } else {
                exception.printStackTrace();
            }
        });

        System.out.println("Tombstone message send initiated to Kafka topic: " + topic);
    }
}
