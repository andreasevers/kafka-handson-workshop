package be.ordina.workshop.kafkaexercises.spring_cloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannels {

    @Input
    SubscribableChannel testCloud();
}
