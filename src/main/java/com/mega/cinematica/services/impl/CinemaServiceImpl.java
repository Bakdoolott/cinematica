package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.CinemaRepository;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.CinemaMapper;
import com.mega.cinematica.microservices.json.FileResponse;
import com.mega.cinematica.microservices.FileService;
import com.mega.cinematica.models.dto.responses.CinemaResponse;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.dto.requests.CreateCinemaRequest;
import com.mega.cinematica.models.dto.responses.Response;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.services.CinemaService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaDto, CinemaRepository, CinemaMapper> implements CinemaService {
    private final FileService fileService;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper, CinemaRepository repository, FileService fileService) {
        super(cinemaRepository, cinemaMapper);
        this.repository = repository;
        this.fileService = fileService;
    }

    @Override
    public CinemaResponse createCinema(CreateCinemaRequest request, MultipartFile file) {
        FileResponse fileResponse;
        CinemaDto cinemaDto = new CinemaDto();

        try {
            fileResponse = fileService.upload(file);
            cinemaDto.setLogo(fileResponse.getDownloadUri());
            cinemaDto.setName(request.getName());
            cinemaDto.setDescription(request.getDescription());
            cinemaDto = saveEntity(cinemaDto);
        }catch (Exception e){
            throw new UnexpectedNewException("notSavedException");
        }
        if(cinemaDto == null)
            throw new NotSavedException(ResourceBundle.periodMessages("unexpectedException"));
        return new CinemaResponse(cinemaDto.getName(), cinemaDto.getDescription(), cinemaDto.getLogo());
    }

    @Override
    public Response deleteCinema(Long id) {
        repository.deleteById(id);
        return new Response(ResourceBundle.periodMessages("deleted"));
    }

    @Override
    public CinemaResponse getCinema(Long id) {
        CinemaResponse cinemaResponse = new CinemaResponse();
        CinemaDto cinema = new CinemaDto();

        try {
             cinema = findById(id);
        }catch (Exception e){
            throw new NotSavedException(
                    ResourceBundle.periodMessages("notSavedException"));
        }

        try {
            cinemaResponse.setDescription(cinema.getDescription());
            cinemaResponse.setName(cinema.getName());
            cinemaResponse.setLogo(cinema.getLogo());
        }catch (Exception e){
            throw new UnexpectedNewException("unexpectedException");
        }
        return cinemaResponse;
    }
}
