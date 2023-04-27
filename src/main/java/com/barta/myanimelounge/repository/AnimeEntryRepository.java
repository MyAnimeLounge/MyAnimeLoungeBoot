package com.barta.myanimelounge.repository;

import com.barta.myanimelounge.models.AnimeEntry;
import org.springframework.data.repository.CrudRepository;

public interface AnimeEntryRepository extends CrudRepository<AnimeEntry, Long> {
}
