package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.CinemaRepository;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.CinemaMapper;
import com.mega.cinematica.mappers.HallMapper;
import com.mega.cinematica.microservices.json.FileResponse;
import com.mega.cinematica.microservices.FileService;
import com.mega.cinematica.models.dto.entityDto.*;
import com.mega.cinematica.models.dto.responses.*;
import com.mega.cinematica.models.entity.Cinema;
import com.mega.cinematica.models.dto.requests.CreateCinemaRequest;
import com.mega.cinematica.models.enums.HallType;
import com.mega.cinematica.services.CinemaService;
import com.mega.cinematica.services.FilmService;
import com.mega.cinematica.services.OrderSessionService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaDto, CinemaRepository, CinemaMapper> implements CinemaService {
    private final FileService fileService;
    private final OrderSessionService orderSessionService;
    private final FilmService filmService;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper, CinemaRepository repository, FileService fileService, OrderSessionService orderSessionService, FilmService filmService) {
        super(cinemaRepository, cinemaMapper);
        this.orderSessionService = orderSessionService;
        this.filmService = filmService;
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

    @Override
    public FilmSessionsResponse sessionsByFilm(Long filmId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<CinemaDto> cinemaDto = mapper.toDtos(repository.findByFilmAndDate(filmId, LocalDate.parse(date, formatter)), context);
        FilmSessionsResponse filmSessionsResponse = new FilmSessionsResponse();
        filmSessionsResponse.setCinemaDetails(new ArrayList<>());
        for(CinemaDto cinemaDto1: cinemaDto){
            List<HallDto> hallDto = HallMapper.MAPPER.toDtos(repository.getHallByParameters(cinemaDto1.getId(), LocalDate.parse(date, formatter), filmId), context);
            CinemaDetails cinemaDetails = new CinemaDetails();
            cinemaDetails.setHallDetails(new ArrayList<>());
            cinemaDetails.setCinemaName(cinemaDto1.getName());
            for(HallDto hallDto1: hallDto){
                HallDetails hallDetails = new HallDetails();
                hallDetails.setSessionDetails(new ArrayList<>());
                List<SessionDto> sessionDtos = orderSessionService.findSessionsByHallAndDate(hallDto1.getId(), LocalDate.parse(date, formatter), filmId);

                hallDetails.setHallName(hallDto1.getName());
                for(SessionDto sessionDto: sessionDtos){
                    SessionDetails sessionDetails = new SessionDetails();
                    sessionDetails.setSessionId(sessionDto.getId());
                    sessionDetails.setChildPrice(sessionDto.getPrice().getPriceForChildren());
                    sessionDetails.setStudentPrice(sessionDto.getPrice().getPriceForStudents());
                    sessionDetails.setStandardPrice(sessionDto.getPrice().getStandardPrice());
                    sessionDetails.setTime(sessionDto.getStartTime());
                    hallDetails.getSessionDetails().add(sessionDetails);
                }
                cinemaDetails.getHallDetails().add(hallDetails);
            }
            filmSessionsResponse.getCinemaDetails().add(cinemaDetails);
        }
        return filmSessionsResponse;
    }

    @Override
    public List<GetCinemas> getAllCinema() {
        return repository.findAllCinemas();
    }

    @Override
    public List<HallsOfCinema> getFilm(Long CinemaId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate;
        List<HallDto> hallDtos;
        try {
            parsedDate = LocalDate.parse(date, formatter);
            hallDtos = HallMapper.MAPPER.toDtos(repository.getCinemasHalls(CinemaId, parsedDate), context);
        }catch (Exception e ){
            throw new UnexpectedNewException(ResourceBundle.periodMessages("unexpectedException"));
        }

        if(hallDtos == null){
            return null;
        }
        List<HallsOfCinema> hallsOfCinemas = new ArrayList<>();
        for(HallDto hallDto: hallDtos){
            HallsOfCinema hallsOfCinema = new HallsOfCinema();
            hallsOfCinema.setFilmsOfHalls(new ArrayList<>());
            hallsOfCinema.setName(hallDto.getName());
            List<FilmDto> filmDtos = filmService.findByHallAndDate(hallDto.getId(), parsedDate);

            for (FilmDto filmDto: filmDtos){
                List<SessionDto> sessionDtos = orderSessionService.findSessionsByHallAndDate(hallDto.getId(), parsedDate, filmDto.getId());
                AllFilmsOfHall filmsOfHalls = new AllFilmsOfHall();
                filmsOfHalls.setName(filmDto.getName());
                filmsOfHalls.setSessionDetails(new ArrayList<>());
                for(SessionDto sessionDto: sessionDtos){
                    SessionDetails sessionDetails1 = new SessionDetails();
                    sessionDetails1.setSessionId(sessionDto.getId());
                    sessionDetails1.setChildPrice(sessionDto.getPrice().getPriceForChildren());
                    sessionDetails1.setStudentPrice(sessionDto.getPrice().getPriceForStudents());
                    sessionDetails1.setStandardPrice(sessionDto.getPrice().getStandardPrice());
                    sessionDetails1.setTime(sessionDto.getStartTime());
                    filmsOfHalls.getSessionDetails().add(sessionDetails1);
                }
                hallsOfCinema.getFilmsOfHalls().add(filmsOfHalls);
            }
            hallsOfCinemas.add(hallsOfCinema);
        }
        return hallsOfCinemas;
    }

    @Override
    public List<CinemaDto> findCinemaByHallType(LocalDate date, HallType hallType) {
        List<CinemaDto> cinemaDtos = mapper.toDtos(repository.findCinemaByHallType(date, hallType), context);
        return cinemaDtos;
    }
}















