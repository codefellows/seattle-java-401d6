package com.ferreirae.songr.models;


import org.springframework.data.jpa.repository.JpaRepository;

// Repositories are the things that let us interact with data in the database (like pg.Client)
// It acts as a client to postgres
public interface ReasonRepository extends JpaRepository<Reason, Long> {

}
