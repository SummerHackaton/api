package org.example.summerhackaton.dao;


import org.example.summerhackaton.domain.model.user.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    RolesEntity getRolesEntitiesById(Long id);
}
