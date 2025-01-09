package ace.charitan.media.internal.media.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ace.charitan.media.internal.media.dto.InternalMediaDto;

public interface InternalMediaService {

    List<InternalMediaDto> uploadImages(String projectId, List<MultipartFile> files);

    List<InternalMediaDto> uploadVideos(String projectId, List<MultipartFile> files);

}
