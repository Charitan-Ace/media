package ace.charitan.media.internal.media.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ace.charitan.media.internal.media.dto.InternalMediaDto;
import ace.charitan.media.internal.media.service.InternalMediaService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class MediaController {

    @Autowired
    private InternalMediaService mediaService;

    @PostMapping("/upload/project/{projectId}/images/{imageId}")
    ResponseEntity<InternalMediaDto> uploadImages(@PathVariable String projectId,
            @RequestParam List<MultipartFile> files) {

        List<InternalMediaDto> mediaDtoList = mediaService.uploadImages(projectId, files);

        

        // List<Map<String, Object>> results = new ArrayList<>();
        // try {
        // for (MultipartFile file : files) {
        // Map<String, Object> result = mediaService.upload(file, "image");
        // results.add(result);
        // }
        // return ResponseEntity.ok(results);
        // } catch (IOException e) {
        // return ResponseEntity.status(500).body("Error uploading images");
        // }
    }

}
