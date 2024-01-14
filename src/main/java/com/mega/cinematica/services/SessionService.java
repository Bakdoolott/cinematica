package com.mega.cinematica.services;

import com.mega.cinematica.base.BaseService;
import com.mega.cinematica.models.dto.entityDto.SessionDto;
import com.mega.cinematica.models.dto.requests.CreateSessionRequest;
import com.mega.cinematica.models.dto.responses.SessionResponse;

public interface SessionService extends BaseService<SessionDto> {
    SessionResponse createSession(CreateSessionRequest request);
}
