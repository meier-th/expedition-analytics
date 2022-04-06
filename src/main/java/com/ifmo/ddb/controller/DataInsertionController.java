package com.ifmo.ddb.controller;

import com.ifmo.ddb.entity.*;
import com.ifmo.ddb.service.DataInsertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insertion")
@Slf4j
@RequiredArgsConstructor
public class DataInsertionController {

    private final DataInsertService dataInsertService;

    @PostMapping("/city")
    public void addCity(@RequestBody City city) {
        dataInsertService.saveCity(city);
    }

    @PostMapping("/crew")
    public void addCrew(@RequestBody Crew crew) {
        dataInsertService.saveCrew(crew);
    }

    @PostMapping("/ship")
    public void addShip(@RequestBody Ship ship) {
        dataInsertService.saveShip(ship);
    }

    @PostMapping("/expedition")
    public void addExpedition(@RequestBody Expedition expedition) {
        dataInsertService.saveExpedition(expedition);
    }

    @PostMapping("/interval")
    public void addInterval(@RequestBody Interval interval) {
        dataInsertService.saveInterval(interval);
    }

    @ExceptionHandler(Exception.class)
    void handleException(Exception error) {
        log.warn(error.getMessage());
    }

}
