package com.ferreirae.songr.models;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository < Type, Identifier type>, we set up the emotion id to be a Long so this is a Long
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
