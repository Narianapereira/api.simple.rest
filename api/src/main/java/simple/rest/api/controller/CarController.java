package simple.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.rest.api.ETagUtil;
import simple.rest.api.car.CarRepository;
import simple.rest.api.car.Car;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarRepository repository;


    @PostMapping
    public void register(@RequestBody String data){
        repository.save(new Car(data));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(@RequestHeader("If-None-Match") String ifNoneMatch){
        List<Car> list = repository.findAll();

        String eTag = ETagUtil.calculateETag(list);
        if(ifNoneMatch.equals(eTag)){
            return ResponseEntity.status(304).build();
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .eTag(eTag)
                .body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@RequestHeader("If-None-Match") Long ifNoneMatch,
                                                @PathVariable Long id){
        Car car = repository.getReferenceById(id);
        Long eTag = car.getUpdatedAt();

        if(ifNoneMatch.equals(eTag)){
            return ResponseEntity.status(304).build();
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .eTag(eTag.toString())
                .body(car);
    }


}