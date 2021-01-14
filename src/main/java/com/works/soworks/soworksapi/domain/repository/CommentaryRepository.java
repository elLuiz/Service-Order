package com.works.soworks.soworksapi.domain.repository;

import com.works.soworks.soworksapi.domain.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
