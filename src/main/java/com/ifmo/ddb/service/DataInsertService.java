package com.ifmo.ddb.service;

import com.ifmo.ddb.dto.Options;
import com.ifmo.ddb.entity.*;

public interface DataInsertService {

    void saveExpedition(Expedition expedition);

    void saveCrew(Crew crew);

    void saveInterval(Interval interval);

    void saveShip(Ship ship);

    void saveCity(City city);

    Options getInsertionOptions();

}
