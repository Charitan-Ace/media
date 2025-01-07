package ace.charitan.media.internal.media.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.MediaEnum.MediaType;

@Service
class MediaServiceImpl implements InternalMediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<InternalMediaDto> uploadImages(String projectId, List<MultipartFile> files) {
        // Check the number of images of the project

        UUID projectIdUuid = UUID.fromString(projectId);

        List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                projectIdUuid);

                if (existedImageList.size() + files.size() > )
    }

}
