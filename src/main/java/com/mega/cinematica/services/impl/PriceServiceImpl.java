package com.mega.cinematica.services.impl;

import com.mega.cinematica.base.BaseServiceImpl;
import com.mega.cinematica.dao.PriceRepository;
import com.mega.cinematica.exceptions.NotSavedException;
import com.mega.cinematica.exceptions.UnexpectedNewException;
import com.mega.cinematica.mappers.PriceMapper;
import com.mega.cinematica.models.dto.entityDto.PriceDto;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.responses.PriceResponse;
import com.mega.cinematica.models.entity.Price;
import com.mega.cinematica.services.PriceService;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl extends BaseServiceImpl<Price, PriceDto, PriceRepository, PriceMapper>implements PriceService {
    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        super(priceRepository, priceMapper);
    }

    @Override
    public PriceResponse createPrice(Integer standardPrice, Integer childPrice, Integer studentPrice, SessionDto session) {
        PriceDto price = new PriceDto();
        try {
            price.setStandardPrice(standardPrice);
            price.setPriceForChildren(childPrice);
            price.setPriceForStudents(studentPrice);
            price.setSession(session);
        }catch (Exception e){
            throw new UnexpectedNewException(ResourceBundle.periodMessages("unexpectedException"));
        }
        try {
            price = saveEntity(price);
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
        PriceResponse response = new PriceResponse();

        try {
            response.setStandardPrice(price.getStandardPrice());
            response.setStudentPrice(price.getPriceForStudents());
            response.setChildPrice(price.getPriceForChildren());
        }catch (Exception e){
            throw new NotSavedException(ResourceBundle.periodMessages("notSavedException"));
        }
        return response;
    }
}
