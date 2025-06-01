package org.example.summerhackaton.domain.service.festival;

import org.example.summerhackaton.domain.model.festival.Festival;
import org.example.summerhackaton.domain.model.festival.SimpleLocation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FestivalService {

    private static final String FESTIVAL_COLLECTION = "festivals";

    private final MongoTemplate mongoTemplate;

    public FestivalService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Festival createFestival(Festival festival) {
        return mongoTemplate.save(festival, FESTIVAL_COLLECTION);
    }

    public Optional<Festival> getFestivalById(String id) {
        Festival festival = mongoTemplate.findById(id, Festival.class, FESTIVAL_COLLECTION);
        return Optional.ofNullable(festival);
    }

    public Festival getFestivalByLocation(SimpleLocation simpleLocation) {
        // calculate if simpleLocation is inside the LocationRange (topLeft and bottomRight
        Double latP = simpleLocation.getX();
        Double lonP = simpleLocation.getY();

        // Construimos el Query usando los mismos campos y operadores que tu consulta en el shell:
        // {
        //   "location.bottomRight.latitude":  { $lte: latP },
        //   "location.topLeft.latitude":      { $gte: latP },
        //   "location.topLeft.longitude":     { $lte: lonP },
        //   "location.bottomRight.longitude": { $gte: lonP }
        // }
        Query query = new Query();
        query.addCriteria(
                Criteria.where("location.bottomRight.latitude").lte(latP)
                        .and("location.topLeft.latitude").gte(latP)
                        .and("location.topLeft.longitude").lte(lonP)
                        .and("location.bottomRight.longitude").gte(lonP)
        );
        Festival festival = mongoTemplate.findOne(query, Festival.class, FESTIVAL_COLLECTION);
        if (festival == null) {
            throw new IllegalArgumentException("Festival not found for the given location");
        }
        return festival;
    }
}


