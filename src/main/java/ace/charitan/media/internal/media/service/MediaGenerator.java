package ace.charitan.media.internal.media.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
class MediaGenerator {
    @Autowired
    MediaServiceImpl mediaService;

    @PostConstruct
    void initData() {
        mediaService.initData();
    }

}
