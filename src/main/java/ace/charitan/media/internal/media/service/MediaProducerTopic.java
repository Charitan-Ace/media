package ace.charitan.media.internal.media.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
enum MediaProducerTopic {
    PROJECT_UPDATE_PROJECT_MEDIA("project.update-project-media");

    private String topic;

}