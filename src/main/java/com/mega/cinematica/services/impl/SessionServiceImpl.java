package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.SessionRepository;
import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.RepeatedValueException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.SessionMapper;
import com.mega.cinematica.models.dto.entityDto.FilmDto;
import com.mega.cinematica.models.dto.entityDto.HallDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.requests.CreateSessionRequest;
import com.mega.cinematica.models.dto.responses.PriceResponse;
import com.mega.cinematica.models.dto.responses.SessionResponse;
import com.mega.cinematica.models.entity.Session;
import com.mega.cinematica.services.FilmService;
import com.mega.cinematica.services.HallService;
import com.mega.cinematica.services.PriceService;
import com.mega.cinematica.services.SessionService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class SessionServiceImpl extends BaseServiceImpl<Session, SessionDto, SessionRepository,
        SessionMapper> implements SessionService {
    private final HallService hallService;
    private final FilmService filmService;
    private final PriceService priceService;
    public SessionServiceImpl(SessionRepository sessionRepository, SessionMapper sessionMapper, HallService hallService, FilmService filmService, PriceService priceService) {
        super(sessionRepository, sessionMapper);
        this.hallService = hallService;
        this.filmService = filmService;
        this.priceService = priceService;
    }


    @Override
    @Transactional
    public SessionResponse createSession(CreateSessionRequest request) {
        HallDto hall = hallService.findById(request.getHallId());
        FilmDto film = filmService.findById(request.getFilmId());

        if(hall == null && film == null){
            throw new NotFoundException(ResourceBundle.periodMessages("notFoundException"));
        }

        SessionDto session = new SessionDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            session.setDate(LocalDate.parse(request.getDate(), formatter));
            session.setStartTime(LocalTime.parse(request.getTime()));
            session.setEndTime(session.getStartTime()
                    .plusHours(film.getLasting().getHour())
                    .plusMinutes(film.getLasting().getMinute() + 9)
                    .plusSeconds(film.getLasting().getSecond() + 59));
        }catch (Exception e){
            throw new UnexpectedNewException(ResourceBundle.periodMessages("unexpectedException"));
        }

        Session oldSession = new Session();
        try {
            oldSession = repository.findByDate(session.getDate(), session.getStartTime(), session.getEndTime(), hall.getId());
        } catch (Exception ignored){}

        if(oldSession != null){
            throw new RepeatedValueException(ResourceBundle.periodMessages("repeatedValue"));
        }

        SessionResponse sessionResponse = new SessionResponse();
        try{
            session.setFilm(film);
            session.setHall(hall);
            if(request.getStudentPrice() == 0){
                session.setStudentPrice(false);
            }else {
                session.setStudentPrice(true);
            }if (request.getChildPrice() == 0){
                session.setChildPrice(false);
            }else {
                session.setChildPrice(true);
            }if (request.getStandardPrice() == 0)
                throw new Exception();
            session = saveEntity(session);
            sessionResponse.setDate(request.getDate());
            sessionResponse.setTime(request.getTime());
            sessionResponse.setFilmName(film.getName());
            sessionResponse.setHallName(hall.getName());
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }

        PriceResponse priceResponse = priceService.createPrice(request.getStandardPrice(),
                request.getChildPrice(), request.getStudentPrice(), session);
        try {
            sessionResponse.setPriceResponse(priceResponse);
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }

        return sessionResponse;
    }
}
