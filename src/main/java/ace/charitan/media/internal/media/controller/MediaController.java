package ace.charitan.media.internal.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.InternalMediaService;

@RestController
class MediaController {

    @Autowired
    private InternalMediaService mediaService;

    @PostMapping("/upload/images/project/{projectId}")
    ResponseEntity<List<InternalMediaDto>> uploadImages(@PathVariable String projectId,
            @RequestParam List<MultipartFile> files) {

        List<InternalMediaDto> mediaDtoList = mediaService.uploadImages(projectId, files);
        return new ResponseEntity<>(mediaDtoList, HttpStatus.OK);
    }


    @PostMapping("/upload/images/project/{projectId}")
    ResponseEntity<List<InternalMediaDto>> uploadVideos(@PathVariable String projectId,
    @RequestParam List<MultipartFile> files
    ) {
        List<InternalMediaDto> mediaDtoList = mediaService.uploadVideos(projectId, files);
        return new ResponseEntity<>(mediaDtoList, HttpStatus.OK);
    }
    
}
