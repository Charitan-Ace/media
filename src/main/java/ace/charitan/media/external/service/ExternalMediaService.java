package ace.charitan.media.external.service;

import ace.charitan.common.dto.media.GetMediaByProjectIdRequestDto;
import ace.charitan.common.dto.media.GetMediaByProjectIdResponseDto;

public interface ExternalMediaService {

    GetMediaByProjectIdResponseDto getMediaByProjectId(GetMediaByProjectIdRequestDto dto);

}