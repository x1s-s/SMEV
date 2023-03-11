package by.x1ss.smev.service;

import by.x1ss.smev.DTO.ResponseList;

import java.util.UUID;

public interface ResponseService {
    ResponseList getResponse(UUID uuid);
    void confirmResponse(UUID uuid);
}
