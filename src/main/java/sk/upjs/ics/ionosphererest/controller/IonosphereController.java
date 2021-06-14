package sk.upjs.ics.ionosphererest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.upjs.ics.ionosphererest.model.*;
import sk.upjs.ics.ionosphererest.repository.IonosphereRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IonosphereController {

    @Autowired
    IonosphereRepository ionosphereRepository;

    @GetMapping("/heatmap")
    public ResponseEntity<List<Heatmap>>
    getHeatmapData(@RequestParam() String attribute,
                     @RequestParam() String station,
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeStart,
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeEnd) {
        try {
            List<Ionosphere> ionospheres = ionosphereRepository
                    .findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(station, timeStart, timeEnd);

//            ionospheres.stream().forEach(System.out::println);

            Map<AzimuthElevation, Heatmap> map = new HashMap<>();
            Heatmap heatmap;
            for(Ionosphere elem: ionospheres){
                AzimuthElevation azimuthElevation = new AzimuthElevation(elem);
                if (map.containsKey(azimuthElevation)){
                    heatmap = map.get(azimuthElevation);

                    if(attribute.equals("tecu")){
                        ((HeatmapTecu)heatmap).setTecu(((HeatmapTecu)heatmap).getTecu() + elem.getTecu());
                    } else if(attribute.equals("s4")){
                        ((HeatmapS4)heatmap).setS4(((HeatmapS4)heatmap).getS4() + elem.getS4());
                    } else if (attribute.equals("sigmaphi")){
                        ((HeatmapSigmaphi)heatmap).setSigmaphi(((HeatmapSigmaphi)heatmap).getSigmaphi() + elem.getSigmaphi());
                    } else {
                        throw new Exception();
                    }

                    map.put(azimuthElevation, heatmap);
                } else {
                    if(attribute.equals("tecu")){
                        heatmap = new HeatmapTecu(elem);
                    } else if(attribute.equals("s4")){
                        heatmap = new HeatmapS4(elem);
                    } else if (attribute.equals("sigmaphi")){
                        heatmap = new HeatmapSigmaphi(elem);
                    } else {
                        throw new Exception();
                    }

                    heatmap.setTimeStart(timeStart);
                    heatmap.setTimeEnd(timeEnd);
                    map.put(azimuthElevation, heatmap);
                }
            }

            long hoursBetween = ChronoUnit.HOURS.between(timeStart, timeEnd);
            List<Heatmap> result = new ArrayList<>();
            map.values()
                    .stream()
                    .map(elem -> {
                        if(attribute.equals("tecu")){
                            ((HeatmapTecu)elem).setTecu((double)Math.round((((HeatmapTecu)elem).getTecu()/hoursBetween)*1000)/1000);
                        } else if(attribute.equals("s4")){
                            ((HeatmapS4)elem).setS4((double)Math.round((((HeatmapS4)elem).getS4()/hoursBetween)*1000)/1000);
                        } else if (attribute.equals("sigmaphi")){
                            ((HeatmapSigmaphi)elem).setSigmaphi((double)Math.round((((HeatmapSigmaphi)elem).getSigmaphi()/hoursBetween)*1000)/1000);
                        }
                        return elem;
                    })
                    .forEach(result::add);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heatmap2")
    public ResponseEntity<List<Heatmap>>
    getHeatmapData2(@RequestParam() String attribute,
                        @RequestParam() String station,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeStart,
                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeEnd) {
        try {
            List<Ionosphere> ionospheres = ionosphereRepository
                    .findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(station, timeStart, timeEnd);

//            ionospheres.stream().forEach(System.out::println);
//            System.out.println(ionospheres.size());

            Map<AzimuthElevation, Heatmap> map = new HashMap<>();
            Map<AzimuthElevation, Integer> map2 = new HashMap<>();
            Heatmap heatmap;
            for(Ionosphere elem: ionospheres){
                AzimuthElevation azimuthElevation = new AzimuthElevation(elem);
                if (map.containsKey(azimuthElevation)){
                    heatmap = map.get(azimuthElevation);

                    if(attribute.equals("tecu")){
                        ((HeatmapTecu)heatmap).setTecu(((HeatmapTecu)heatmap).getTecu() + elem.getTecu());
                    } else if(attribute.equals("s4")){
                        ((HeatmapS4)heatmap).setS4(((HeatmapS4)heatmap).getS4() + elem.getS4());
                    } else if (attribute.equals("sigmaphi")){
                        ((HeatmapSigmaphi)heatmap).setSigmaphi(((HeatmapSigmaphi)heatmap).getSigmaphi() + elem.getSigmaphi());
                    } else {
                        throw new Exception();
                    }

                    map.put(azimuthElevation, heatmap);
                    map2.put(azimuthElevation, map2.get(azimuthElevation)+1);
                } else {
                    if(attribute.equals("tecu")){
                        heatmap = new HeatmapTecu(elem);
                    } else if(attribute.equals("s4")){
                        heatmap = new HeatmapS4(elem);
                    } else if (attribute.equals("sigmaphi")){
                        heatmap = new HeatmapSigmaphi(elem);
                    } else {
                        throw new Exception();
                    }

                    heatmap.setTimeStart(timeStart);
                    heatmap.setTimeEnd(timeEnd);
                    map.put(azimuthElevation, heatmap);
                    map2.put(azimuthElevation, 1);
                }
            }

            List<Heatmap> result = new ArrayList<>();
            map.values()
                    .stream()
                    .map(elem -> {
                        int count = map2.get(new AzimuthElevation(elem));
                        if(attribute.equals("tecu")){
                            ((HeatmapTecu)elem).setTecu((double)Math.round((((HeatmapTecu)elem).getTecu()/count)*1000)/1000);
                        } else if(attribute.equals("s4")){
                            ((HeatmapS4)elem).setS4((double)Math.round((((HeatmapS4)elem).getS4()/count)*1000)/1000);
                        } else if (attribute.equals("sigmaphi")){
                            ((HeatmapSigmaphi)elem).setSigmaphi((double)Math.round((((HeatmapSigmaphi)elem).getSigmaphi()/count)*1000)/1000);
                        }
                        return elem;
                    })
                    .forEach(result::add);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/scatter")
    public ResponseEntity<List<Data>>
    getScatterData(@RequestParam() String attribute,
                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeStart,
                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeEnd){
        try {
            List<Ionosphere> ionospheres =
                    ionosphereRepository.findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(timeStart, timeEnd);

//            ionospheres.stream().forEach(System.out::println);

            Map<StationTimeStartEnd, Data> map = new HashMap<>();
            Data data;
            for(Ionosphere elem: ionospheres){
                StationTimeStartEnd stationTimeStartEnd = new StationTimeStartEnd(elem);
                if (map.containsKey(stationTimeStartEnd)){
                    data = map.get(stationTimeStartEnd);

                    if(attribute.equals("tecu")){
                        ((ScatterTecu)data).setTecu(((ScatterTecu)data).getTecu() + elem.getTecu());
                    } else if(attribute.equals("s4")){
                        ((ScatterS4)data).setS4(((ScatterS4)data).getS4() + elem.getS4());
                    } else if (attribute.equals("sigmaphi")){
                        ((ScatterSigmaphi)data).setSigmaphi(((ScatterSigmaphi)data).getSigmaphi() + elem.getSigmaphi());
                    } else {
                        throw new Exception();
                    }

                    map.put(stationTimeStartEnd, data);
                } else {
                    if(attribute.equals("tecu")){
                        data = new ScatterTecu(elem);
                    } else if(attribute.equals("s4")){
                        data = new ScatterS4(elem);
                    } else if (attribute.equals("sigmaphi")){
                        data = new ScatterSigmaphi(elem);
                    } else {
                        throw new Exception();
                    }

                    map.put(stationTimeStartEnd, data);
                }
            }

            int numOfCombinations = 9*12;
            List<Data> result = new ArrayList<>();
            map.values()
                    .stream()
                    .map(elem -> {
                        if(attribute.equals("tecu")){
                            ((ScatterTecu)elem).setTecu((double)Math.round((((ScatterTecu)elem).getTecu()/numOfCombinations)*1000)/1000);
                        } else if(attribute.equals("s4")){
                            ((ScatterS4)elem).setS4((double)Math.round((((ScatterS4)elem).getS4()/numOfCombinations)*1000)/1000);
                        } else if (attribute.equals("sigmaphi")){
                            ((ScatterSigmaphi)elem).setSigmaphi((double)Math.round((((ScatterSigmaphi)elem).getSigmaphi()/numOfCombinations)*1000)/1000);
                        }
                        return elem;
                    })
                    .forEach(result::add);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/scatter2")
    public ResponseEntity<List<Data>>
    getScatterData2(@RequestParam() String attribute,
                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeStart,
                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant timeEnd){
        try {
            List<Ionosphere> ionospheres =
                    ionosphereRepository.findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(timeStart, timeEnd);

//            ionospheres.stream().forEach(System.out::println);

            Map<StationTimeStartEnd, Data> map = new HashMap<>();
            Map<StationTimeStartEnd, Integer> map2 = new HashMap<>();
            Data data;
            for(Ionosphere elem: ionospheres){
                StationTimeStartEnd stationTimeStartEnd = new StationTimeStartEnd(elem);
                if (map.containsKey(stationTimeStartEnd)){
                    data = map.get(stationTimeStartEnd);

                    if(attribute.equals("tecu")){
                        ((ScatterTecu)data).setTecu(((ScatterTecu)data).getTecu() + elem.getTecu());
                    } else if(attribute.equals("s4")){
                        ((ScatterS4)data).setS4(((ScatterS4)data).getS4() + elem.getS4());
                    } else if (attribute.equals("sigmaphi")){
                        ((ScatterSigmaphi)data).setSigmaphi(((ScatterSigmaphi)data).getSigmaphi() + elem.getSigmaphi());
                    } else {
                        throw new Exception();
                    }

                    map.put(stationTimeStartEnd, data);
                    map2.put(stationTimeStartEnd, map2.get(stationTimeStartEnd)+1);
                } else {
                    if(attribute.equals("tecu")){
                        data = new ScatterTecu(elem);
                    } else if(attribute.equals("s4")){
                        data = new ScatterS4(elem);
                    } else if (attribute.equals("sigmaphi")){
                        data = new ScatterSigmaphi(elem);
                    } else {
                        throw new Exception();
                    }

                    map.put(stationTimeStartEnd, data);
                    map2.put(stationTimeStartEnd, 1);
                }
            }

            List<Data> result = new ArrayList<>();
            map.values()
                    .stream()
                    .map(elem -> {
                        int count = map2.get(new StationTimeStartEnd(elem));
                        if(attribute.equals("tecu")){
                            ((ScatterTecu)elem).setTecu((double)Math.round((((ScatterTecu)elem).getTecu()/count)*1000)/1000);
                        } else if(attribute.equals("s4")){
                            ((ScatterS4)elem).setS4((double)Math.round((((ScatterS4)elem).getS4()/count)*1000)/1000);
                        } else if (attribute.equals("sigmaphi")){
                            ((ScatterSigmaphi)elem).setSigmaphi((double)Math.round((((ScatterSigmaphi)elem).getSigmaphi()/count)*1000)/1000);
                        }
                        return elem;
                    })
                    .forEach(result::add);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> findByTimeStart() {
        try {
            Integer count = ionosphereRepository.getCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Ionosphere> test() {
        try {
            System.out.println(Instant.now());
            Ionosphere ionosphere = ionosphereRepository.getById();
            return new ResponseEntity<>(ionosphere, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/test2")
//    public ResponseEntity<List<Ionosphere>> test2() {
//        try {
//            List<Ionosphere> ionosphere = ionosphereRepository.findByTimeStartEqual();
//            return new ResponseEntity<>(ionosphere, HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
