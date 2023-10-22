package simple.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.rest.api.ETagUtil;
import simple.rest.api.animal.Animal;
import simple.rest.api.animal.AnimalRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;


    @PostMapping
    public void register(@RequestBody String data){
        repository.save(new Animal(data));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAnimals(@RequestHeader("If-None-Match") String ifNoneMatch){
        List<Animal> list = repository.findAll();

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
    public ResponseEntity<Animal> getAnimalById(@RequestHeader("If-None-Match") Long ifNoneMatch,
                                          @PathVariable Long id){
        Animal animal = repository.getReferenceById(id);
        Long eTag = animal.getUpdatedAt();

        if(ifNoneMatch.equals(eTag)){
            return ResponseEntity.status(304).build();
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .eTag(eTag.toString())
                .body(animal);
    }


}
