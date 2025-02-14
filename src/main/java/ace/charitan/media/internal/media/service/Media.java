package ace.charitan.media.internal.media.service;

import java.util.UUID;

import ace.charitan.common.dto.media.ExternalMediaDto;
import ace.charitan.media.internal.common.AbstractEntity;
import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.MediaEnum.MediaType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Media extends AbstractEntity implements InternalMediaDto {

    private String mediaUrl;

    private String publicId;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    private String mediaFormat;

    private String resourceType;

    private Boolean isThumbnail;

    private String projectId;

    ExternalMediaDto toExternalMediaDto() {
        return new ExternalMediaDto(mediaUrl, publicId, mediaType.getValue(), mediaFormat, resourceType, isThumbnail,
                projectId);
    }

}
