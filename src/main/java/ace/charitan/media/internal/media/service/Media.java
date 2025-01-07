package ace.charitan.media.internal.media.service;

import ace.charitan.media.internal.common.AbstractEntity;
import ace.charitan.media.internal.media.dto.InternalMediaDto;
import jakarta.persistence.Entity;
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

    private String url;

    private String publicId;

    private String mediaType;

    private String format;

    private String resourceType;

}
