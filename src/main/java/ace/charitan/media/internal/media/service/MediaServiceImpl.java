package ace.charitan.media.internal.media.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import ace.charitan.common.dto.media.ExternalMediaDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdRequestDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdResponseDto;
import ace.charitan.common.dto.media.MediaListDto;
import ace.charitan.media.config.MediaConstant;
import ace.charitan.media.external.service.ExternalMediaService;
import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.MediaEnum.MediaType;
import jakarta.transaction.Transactional;

@Service
class MediaServiceImpl implements InternalMediaService, ExternalMediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private Cloudinary cloudinary;

    @SuppressWarnings("unchecked")
    private Media uploadMedia(MultipartFile file, Map<String, String> options, MediaType mediaType, boolean isThumbnail,
            String projectId) {
        try {
            System.out.println("Start to upload images");
            Map<String, Object> uploadResponse = (Map<String, Object>) cloudinary.uploader().upload(file.getBytes(),
                    options);

            System.out.println("End to upload images");
            String mediaUrl = (String) uploadResponse.get("secure_url");
            String publicId = (String) uploadResponse.get("public_id");
            String mediaFormat = (String) uploadResponse.get("format");
            String resourceType = (String) uploadResponse.get("resource_type");
            Media mediaEntity = new Media(mediaUrl, publicId, mediaType, mediaFormat, resourceType, isThumbnail,
                    projectId);

            return mediaEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<InternalMediaDto> uploadImages(String projectId, List<MultipartFile> files) {
        try {

            // Check the number of images of the project

            // UUID projectIdUuid = UUID.fromString(projectId);
            // UUID projectIdUuid = UUID.randomUUID();

            List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                    projectId).stream().map(d -> d).collect(Collectors.toList());

            if (existedImageList.size() + files.size() > MediaConstant.MAX_IMAGES) {
                // TODO: MAx images allowed
            }

            boolean hasThumbnail = !mediaRepository.findAllByIsThumbnailAndProjectId(true, projectId).isEmpty();

            List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

            for (MultipartFile file : files) {
                Media mediaEntity = uploadMedia(file, (Map<String, String>) ObjectUtils.asMap(
                        "folder", "charitan/image/project"), MediaType.IMAGE, false, projectId);

                if (Objects.isNull(mediaEntity)) {
                    continue;
                }

                mediaEntity = mediaRepository.save(mediaEntity);
                internalMediaDtoList.add(mediaEntity);

                if (hasThumbnail) {
                    continue;
                }

                Media thumbnailMediaEntity = uploadMedia(file, ObjectUtils.asMap(
                        "folder", "charitan/image/project", "width", 150, "height", 100, "crop",
                        "fill"),
                        MediaType.IMAGE,
                        true,
                        projectId);

                if (Objects.isNull(thumbnailMediaEntity)) {
                    continue;
                }

                thumbnailMediaEntity = mediaRepository.save(thumbnailMediaEntity);
                internalMediaDtoList.add(thumbnailMediaEntity);
                hasThumbnail = true;
            }

            System.out.println(internalMediaDtoList.size());
            return internalMediaDtoList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InternalMediaDto> uploadVideos(String projectId, List<MultipartFile> files) {
        List<InternalMediaDto> existedImageList = mediaRepository.findAllByMediaTypeAndProjectId(MediaType.IMAGE,
                projectId).stream().map(d -> d).collect(Collectors.toList());

        if (existedImageList.size() + files.size() > MediaConstant.MAX_VIDEOS) {
            // TODO: MAx images allowed
        }

        List<InternalMediaDto> internalMediaDtoList = new ArrayList<>();

        for (MultipartFile file : files) {
            Media mediaEntity = uploadMedia(file, ObjectUtils.asMap("folder", "charitan/video/project"),
                    MediaType.VIDEO, false, projectId);

            if (Objects.isNull(mediaEntity)) {
                continue;
            }

            mediaEntity = mediaRepository.save(mediaEntity);
            internalMediaDtoList.add(mediaEntity);
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
                                media.getIsThumbnail(),
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

    @Override
    public String getThumbnailUrl(String publicId) {
        return cloudinary.url().transformation(new Transformation<>().width(150).height(100).crop("thumb"))
                .generate(publicId);

    }

}
