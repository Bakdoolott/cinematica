package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.requests.CreateSessionRequest;
import com.mega.cinematica.models.dto.responses.SessionResponse;
import com.mega.cinematica.models.entity.OrderSession;

import java.util.List;

public interface SessionService extends BaseService<SessionDto> {
    SessionResponse createSession(CreateSessionRequest request);

}
