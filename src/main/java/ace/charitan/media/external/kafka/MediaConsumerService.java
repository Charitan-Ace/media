package ace.charitan.media.external.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import ace.charitan.common.dto.media.GetMediaByProjectIdRequestDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdResponseDto;
import ace.charitan.media.external.kafka.topic.ProjectProducerTopic;
import ace.charitan.media.internal.media.service.InternalMediaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class MediaConsumerService {

    @Autowired
    private InternalMediaService mediaService;

    // private final ObjectMapper objectMapper;

    @KafkaListener(topics = ProjectProducerTopic.PROJECT_MEDIA_GET_MEDIA_BY_PROJECT_ID)
    @SendTo
    GetMediaByProjectIdResponseDto handleGetMediaByProjectId(GetMediaByProjectIdRequestDto dto) {
        System.out.println(dto.getProjectIdList());
        return mediaService.handleGetMediaByProjectId(dto);
    }

}
