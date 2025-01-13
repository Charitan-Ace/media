package ace.charitan.media.internal.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.InternalMediaService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
class MediaController {

    @Autowired
    private InternalMediaService mediaService;

    // @GetMapping("/health")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

    @PostMapping("/upload/image/project/{projectId}")
    ResponseEntity<List<InternalMediaDto>> uploadImages(@PathVariable String projectId,
            @RequestBody List<MultipartFile> files) {
        List<InternalMediaDto> mediaDtoList = mediaService.uploadImages(projectId, files);
        System.out.println(mediaDtoList);
        return new ResponseEntity<>(mediaDtoList, HttpStatus.OK);
    }

    @PostMapping("/upload/video/project/{projectId}")
    ResponseEntity<List<InternalMediaDto>> uploadVideos(@PathVariable String projectId,
            @RequestBody List<MultipartFile> files) {
        List<InternalMediaDto> mediaDtoList = mediaService.uploadVideos(projectId, files);
        return new ResponseEntity<>(mediaDtoList, HttpStatus.OK);
    }

    @GetMapping("/thumbnail/image/{publicId}")
    public ResponseEntity<String> getThumbnailUrl(@PathVariable String publicId) {
        String thumbnailUrl = mediaService.getThumbnailUrl(publicId);
        return new ResponseEntity<>(thumbnailUrl, HttpStatus.OK);
    }

}
