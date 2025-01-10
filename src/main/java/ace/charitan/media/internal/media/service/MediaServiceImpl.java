package ace.charitan.media.internal.media.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ace.charitan.common.dto.media.ExternalMediaDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdRequestDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdResponseDto;
import ace.charitan.common.dto.media.MediaListDto;
import ace.charitan.media.config.MediaConstant;
import ace.charitan.media.external.service.ExternalMediaService;
import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.MediaEnum.MediaType;

@Service
class MediaServiceImpl implements InternalMediaService, ExternalMediaService {

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
                projectId).stream().map(d -> d).collect(Collectors.toList());

        if (existedImageList.size() + files.size() > MediaConstant.MAX_IMAGES) {
            // TODO: MAx images allowed
        }

        List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, Object> uploadResponse = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "folder", "charitan/project"));
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
                projectId).stream().map(d -> d).collect(Collectors.toList());

        if (existedImageList.size() + files.size() > MediaConstant.MAX_VIDEOS) {
            // TODO: MAx images allowed
        }

        List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                @SuppressWarnings("unchecked")
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

    @Override
    public GetMediaByProjectIdResponseDto getMediaByProjectId(GetMediaByProjectIdRequestDto dto) {
        List<String> projectIdList = dto.getProjectIdList();

        // GetMediaByProjectIdResponseDto response = new
        // GetMediaByProjectIdResponseDto();

        List<MediaListDto> mediaListDtoList = new ArrayList<>();

        for (String projectId : projectIdList) {
            List<Media> list = mediaRepository.findAllByProjectId(projectId);

            List<ExternalMediaDto> externalMediaDtos = list.stream()
                    .map(media -> {
                        return new ExternalMediaDto(
                                media.getMediaUrl(),
                                media.getPublicId(),
                                media.getMediaType().toString(),
                                media.getMediaFormat(),
                                media.getResourceType(),
                                media.getProjectId());
                    })
                    .collect(Collectors.toList());

            mediaListDtoList.add(new MediaListDto(projectId, externalMediaDtos));

        }

        return new GetMediaByProjectIdResponseDto(mediaListDtoList);
    }

    void initData() {

    }

    @Override
    public GetMediaByProjectIdResponseDto handleGetMediaByProjectId(GetMediaByProjectIdRequestDto requestDto) {
        List<String> projectIdList = requestDto.getProjectIdList();

        List<MediaListDto> mediaListDtoList = new ArrayList<>();

        for (String projectId : projectIdList) {
            List<Media> mediaList = mediaRepository.findAllByProjectId(projectId);

            List<ExternalMediaDto> externalMediaDtoList = mediaList.stream()
                    .map(mDto -> mDto.toExternalMediaDto()).toList();

            mediaListDtoList.add(new MediaListDto(projectId, externalMediaDtoList));
        }

        System.out.println(mediaListDtoList);

        return new GetMediaByProjectIdResponseDto(mediaListDtoList);
    }

}
