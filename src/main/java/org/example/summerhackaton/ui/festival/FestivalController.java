package org.example.summerhackaton.ui.festival;

import org.example.summerhackaton.domain.model.festival.Festival;
import org.example.summerhackaton.domain.model.festival.SimpleLocation;
import org.example.summerhackaton.domain.service.festival.FestivalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/festival")
public class FestivalController {

    private final FestivalService festivalService;

    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @PostMapping("/add")
    public ResponseEntity<Festival> createFestival(@RequestBody Festival festival) {
        Festival created = festivalService.createFestival(festival);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Festival> getFestivalById(@PathVariable String id) {
        return festivalService.getFestivalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/findByLocation")
    public ResponseEntity<Festival> getFestivalByLocation(@RequestBody SimpleLocation simpleLocation) {
        return ResponseEntity.ok(festivalService.getFestivalByLocation(simpleLocation));
    }
}

