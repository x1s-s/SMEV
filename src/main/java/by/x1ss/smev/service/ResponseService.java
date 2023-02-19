package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseQueue;

import java.util.UUID;

public interface ResponseService {
    ResponseQueue getResponse(UUID uuid);
    void confirmResponse(UUID uuid);
}
