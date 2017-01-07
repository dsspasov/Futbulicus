package com.fmi.futbulicus.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fmi.futbulicus.model.Match;

public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

}
