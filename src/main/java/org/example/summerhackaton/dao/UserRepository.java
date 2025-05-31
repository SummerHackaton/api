package org.example.summerhackaton.dao;


import org.example.summerhackaton.domain.model.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(attributePaths = {"roles"})
    @Query("select u from UserEntity u")
    List<UserEntity> getAllWithPermisos();

    @EntityGraph(attributePaths = {"visitas"})
    Optional<UserEntity> findWithVisitasById(Long id);

    @EntityGraph(attributePaths = {"codigo"})
    UserEntity findByCodigo(String codigo);
}