package com.kapsi.repository;

import com.kapsi.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

    public CurrentUserSession findByUuid(String uuid);

    @Query("FROM CurrentUserSession a WHERE a.userId=?1")
    public Optional<CurrentUserSession> findByUserId(Integer userId);

}
