package org.example.summerhackaton.dao;


import org.example.summerhackaton.domain.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    RolesEntity getRolesEntitiesById(Long id);
}
