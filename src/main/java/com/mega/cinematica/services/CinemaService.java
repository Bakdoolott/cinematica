package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.requests.CreateCinemaRequest;
import com.mega.cinematica.models.dto.responses.CinemaResponse;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import org.springframework.web.multipart.MultipartFile;

public interface CinemaService extends BaseService<CinemaDto> {
    CinemaResponse createCinema(CreateCinemaRequest request, MultipartFile file);
    Response deleteCinema(Long id);

    CinemaResponse getCinema(Long id);
}
