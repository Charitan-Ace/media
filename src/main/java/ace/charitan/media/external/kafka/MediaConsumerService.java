package ace.charitan.media.external.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import ace.charitan.common.dto.media.GetMediaByProjectIdRequestDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdResponseDto;
import ace.charitan.media.external.kafka.topic.ProjectProducerTopic;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class MediaConsumerService {

    // private final ObjectMapper objectMapper;

    @KafkaListener(topics = ProjectProducerTopic.PROJECT_MEDIA_GET_MEDIA_BY_PROJECT_ID, groupId = "media")
    @SendTo(KafkaHeaders.REPLY_TOPIC)
    GetMediaByProjectIdResponseDto handleGetMediaByProjectId(GetMediaByProjectIdRequestDto dto) {
        System.out.println(dto.getProjectIdList());
        return new GetMediaByProjectIdResponseDto();
    }

}
