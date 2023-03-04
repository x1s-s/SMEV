package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.exception.NotFoundPenaltyException;

import java.util.UUID;

public interface ResponseService {
    ResponseQueue getResponse(UUID uuid) throws NotFoundPenaltyException;
    void confirmResponse(UUID uuid);
}
