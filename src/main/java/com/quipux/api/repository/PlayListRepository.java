package com.quipux.api.repository;

import com.quipux.api.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, String> {
}
