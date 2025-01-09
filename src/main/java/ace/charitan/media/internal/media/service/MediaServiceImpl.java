package ace.charitan.media.internal.media.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        // UUID projectIdUuid = UUID.randomUUID();

        List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                projectId);

        if (existedImageList.size() + files.size() > MediaConstant.MAX_IMAGES) {
            // TODO: MAx images allowed
        }

        List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map<String, Object> uploadResponse = cloudinary.uploader().upload(file.getBytes(), Map.of());
                String mediaUrl = (String) uploadResponse.get("secure_url");
                String publicId = (String) uploadResponse.get("public_id");
                MediaType mediaType = MediaType.IMAGE;
                String mediaFormat = (String) uploadResponse.get("format");
                String resourceType = (String) uploadResponse.get("resource_type");
                Media mediaEntity = new Media(mediaUrl, publicId, mediaType, mediaFormat, resourceType, projectId);
                mediaEntity = mediaRepository.save(mediaEntity);
                internalMediaDtoList.add(mediaEntity);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return internalMediaDtoList;
    }

    @Override
    public List<InternalMediaDto> uploadVideos(String projectId, List<MultipartFile> files) {
        List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                projectId);

        if (existedImageList.size() + files.size() > MediaConstant.MAX_VIDEOS) {
            // TODO: MAx images allowed
        }

        List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map<String, Object> uploadResponse = cloudinary.uploader().upload(file.getBytes(), Map.of());
                String mediaUrl = (String) uploadResponse.get("secure_url");
                String publicId = (String) uploadResponse.get("public_id");
                MediaType mediaType = MediaType.VIDEO;
                String mediaFormat = (String) uploadResponse.get("format");
                String resourceType = (String) uploadResponse.get("resource_type");
                Media mediaEntity = new Media(mediaUrl, publicId, mediaType, mediaFormat, resourceType, projectId);
                mediaEntity = mediaRepository.save(mediaEntity);
                internalMediaDtoList.add(mediaEntity);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return internalMediaDtoList;
    }

}
