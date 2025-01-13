package ace.charitan.media.internal.media.service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import ace.charitan.common.dto.project.UpdateProjectMediaDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;

import ace.charitan.media.external.kafka.topic.ProjectProducerTopic;

@Component
class MediaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private void send(MediaProducerTopic topic, Serializable data) {
        try {
            kafkaTemplate.send(topic.getTopic(), data);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    void send(UpdateProjectMediaDto.UpdateProjectMediaRequestDto requestDto) {
        send(MediaProducerTopic.PROJECT_UPDATE_PROJECT_MEDIA, requestDto);
    }
}
