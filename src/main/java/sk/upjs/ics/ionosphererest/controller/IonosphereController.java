package sk.upjs.ics.ionosphererest.controller;

import com.sun.jdi.InterfaceType;
import org.apache.tinkerpop.gremlin.structure.io.Io;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.upjs.ics.ionosphererest.model.Ionosphere;
import sk.upjs.ics.ionosphererest.model.S4;
import sk.upjs.ics.ionosphererest.model.Sigmaphi;
import sk.upjs.ics.ionosphererest.model.Tecu;
import sk.upjs.ics.ionosphererest.repository.IonosphereRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IonosphereController {

    @Autowired
    IonosphereRepository ionosphereRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Ionosphere>> getAll() {
        try {
            List<Ionosphere> all = new ArrayList<>();
            ionosphereRepository.findAll().forEach(all::add);
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byStation")
    public ResponseEntity<List<Ionosphere>> findByStation(
            @RequestParam String station) {
        try {
            List<Ionosphere> list = new ArrayList<>();
            ionosphereRepository.findByStation(station)
                    .forEach(list::add);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byTime")
    public ResponseEntity<List<Ionosphere>> findByTimeStart(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart) {
        try {
            List<Ionosphere> list = new ArrayList<>();
            ionosphereRepository.findByTimeStart(timeStart)
                    .forEach(list::add);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byStationAndTime")
    public ResponseEntity<List<Ionosphere>> findByStationAndTimeStart(@RequestParam String station,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart) {
        try {
            List<Ionosphere> list = new ArrayList<>();
            ionosphereRepository.findByStationAndTimeStart(station, timeStart)
                    .forEach(list::add);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/betweenTimes")
    public <T> ResponseEntity<List<T>>
    findBetweenTimes(@RequestParam(required = false, defaultValue = "ionosphere") String param,
                     @RequestParam(required = false, defaultValue = "all") String station,
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeStart,
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeEnd) {
        try {
            Class type;

            if(param.equals("tecu")){
                type = Tecu.class;
            } else if (param.equals("s4")) {
                type = S4.class;
            } else if (param.equals("sigmaphi")) {
                type = Sigmaphi.class;
            } else {
                type = Ionosphere.class;
            }

            if (station.equals("all")){
                List<T> list = ionosphereRepository
                        .findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(type, timeStart, timeEnd);
                return new ResponseEntity(list, HttpStatus.OK);
            } else {
                List<T> list = ionosphereRepository
                        .findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(type, station, timeStart, timeEnd);
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
