package com.ifmo.ddb.service.impl;

import com.ifmo.ddb.dto.Options;
import com.ifmo.ddb.entity.*;
import com.ifmo.ddb.entity.repo.*;
import com.ifmo.ddb.service.DataInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInsertServiceImpl implements DataInsertService {

    private final CityRepository cityRepository;
    private final CrewRepository crewRepository;
    private final ExpeditionRepository expeditionRepository;
    private final IntervalRepository intervalRepository;
    private final ShipRepository shipRepository;

    @Override
    public void saveExpedition(Expedition expedition) {
        if (cityRepository.findById(expedition.getStartCity().getId()).isEmpty()) {
            cityRepository.save(expedition.getStartCity());
        }
        if (!cityRepository.exists(Example.of(expedition.getFinishCity()))) {
            cityRepository.save(expedition.getFinishCity());
        }
        if (!shipRepository.exists(Example.of(expedition.getShip()))) {
            shipRepository.save(expedition.getShip());
        }
        if (!crewRepository.exists(Example.of(expedition.getCrew()))) {
            crewRepository.save(expedition.getCrew());
        }
        intervalRepository.save(expedition.getInterval());
        expeditionRepository.save(expedition);
    }

    @Override
    public void saveCrew(Crew crew) {
        crewRepository.save(crew);
    }

    @Override
    public void saveInterval(Interval interval) {
        intervalRepository.save(interval);
    }

    @Override
    public void saveShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public Options getInsertionOptions() {
        Options options = new Options();
        options.setCrews(crewRepository.findAll());
        options.setCities(cityRepository.findAll());
        options.setShips(shipRepository.findAll());
        return options;
    }
}
