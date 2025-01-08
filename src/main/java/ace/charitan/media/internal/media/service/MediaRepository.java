package ace.charitan.media.internal.media.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.MediaEnum.MediaType;

@Repository
interface MediaRepository extends JpaRepository<Media, UUID> {
    List<InternalMediaDto> findAllByMediaTypeAndProjectId(MediaType mediaType, String projectId);

}
