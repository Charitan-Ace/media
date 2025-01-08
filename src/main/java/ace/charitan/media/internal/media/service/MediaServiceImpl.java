package ace.charitan.media.internal.media.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import ace.charitan.media.config.MediaConstant;
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

        // UUID projectIdUuid = UUID.fromString(projectId);
        UUID projectIdUuid = UUID.randomUUID();

        List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                projectIdUuid);

        if (existedImageList.size() + files.size() > MediaConstant.MAX_IMAGES) {
            // TODO: MAx images allowed
        }

        for (MultipartFile file : files) {
            try {
                Map<String, Object> uploadResponse = cloudinary.uploader().upload(file.getBytes(), Map.of());
                System.out.println(uploadResponse);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }

}
