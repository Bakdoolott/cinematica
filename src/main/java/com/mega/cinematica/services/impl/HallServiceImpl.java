package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.HallRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.mappers.HallMapper;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.responses.*;
import com.mega.cinematica.models.entity.Hall;
import com.mega.cinematica.models.dto.entityDto.CinemaDto;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.enums.HallType;
import com.mega.cinematica.services.CinemaService;
import com.mega.cinematica.services.FilmService;
import com.mega.cinematica.services.HallService;
import com.mega.cinematica.services.OrderSessionService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallDto, HallRepository, HallMapper> implements HallService {
    private final CinemaService cinemaService;
    private final FilmService filmService;
    private final OrderSessionService orderSessionService;
    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper, CinemaService cinemaService, FilmService filmService, OrderSessionService orderSessionService) {
        super(hallRepository, hallMapper);
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.orderSessionService = orderSessionService;
    }

    @Override
    public HallResponse createHall(String name, Long cinemaId, HallType hallType) {
        if(repository.findByNameAndCinemaId(name, cinemaId) != null)
            throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
        HallDto hallDto = new HallDto();
        hallDto.setName(name);
        hallDto.setHallType(hallType);
        CinemaDto cinemaDto;

        cinemaDto = cinemaService.findById(cinemaId);

        if(cinemaDto == null)
            throw new NotFoundException(ResourceBundle.periodMessages("notFoundException"));

        hallDto.setCinema(cinemaDto);
        try {
            saveEntity(hallDto);
        }catch (Exception e) {
            throw new NotFoundException(ResourceBundle.periodMessages("notSavedException"));
        }
        return new HallResponse(name, cinemaDto.getName(), hallType);
    }

    @Override
    public Response deleteHall(Long id) {
        repository.deleteById(id);
        return new Response(ResourceBundle.periodMessages("deleted"));
    }

    @Override
    public List<CinemasOfHall> getHallByType(String notParseDate, String type) {
        LocalDate date = LocalDate.parse(notParseDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        HallType hallType = HallType.ORDINARY;
        if(type.equals("imax")){
            hallType = HallType.IMAX;
        } else if(type.equals("atmos")){
            hallType = HallType.ATMOS;
        }

        List<CinemasOfHall> cinemasOfHalls  = new ArrayList<>();
        List<CinemaDto> cinemaDtos = cinemaService.findCinemaByHallType(date, hallType);
        for(CinemaDto cinemaDto: cinemaDtos){
            CinemasOfHall  cinemasOfHall = new CinemasOfHall();
            cinemasOfHall.setName(cinemaDto.getName());
            cinemasOfHall.setHallsOfCinemas(new ArrayList<>());
            List<HallDto>  hallDtos = mapper.toDtos(repository.getHallByType(date, hallType, cinemaDto.getId()), context);

            List<HallsOfCinema> hallsOfCinemas = new ArrayList<>();
            for(HallDto hallDto: hallDtos){
                HallsOfCinema hallsOfCinema = new HallsOfCinema();
                hallsOfCinema.setFilmsOfHalls(new ArrayList<>());
                hallsOfCinema.setName(hallDto.getName());
                List<FilmDto> filmDtos = filmService.findByHallAndDate(hallDto.getId(), date);

                for(FilmDto filmDto: filmDtos){
                    List<SessionDto> sessionDtos = orderSessionService.findSessionsByHallAndDate(hallDto.getId(), date, filmDto.getId());
                    AllFilmsOfHall allFilmsOfHall = new AllFilmsOfHall();
                    allFilmsOfHall.setName(filmDto.getName());
                    allFilmsOfHall.setSessionDetails(new ArrayList<>());

                    for(SessionDto sessionDto: sessionDtos){
                        SessionDetails sessionDetails1 = new SessionDetails();
                        sessionDetails1.setSessionId(sessionDto.getId());
                        sessionDetails1.setChildPrice(sessionDto.getPrice().getPriceForChildren());
                        sessionDetails1.setStudentPrice(sessionDto.getPrice().getPriceForStudents());
                        sessionDetails1.setStandardPrice(sessionDto.getPrice().getStandardPrice());
                        sessionDetails1.setTime(sessionDto.getStartTime());
                        allFilmsOfHall.getSessionDetails().add(sessionDetails1);
                    }
                    hallsOfCinema.getFilmsOfHalls().add(allFilmsOfHall);
                }
                hallsOfCinemas.add(hallsOfCinema);
            }
            cinemasOfHall.setHallsOfCinemas(hallsOfCinemas);
            cinemasOfHalls.add(cinemasOfHall);
        }
        return cinemasOfHalls;
    }
}
