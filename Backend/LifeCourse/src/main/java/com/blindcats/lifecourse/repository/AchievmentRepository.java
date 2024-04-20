package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Achievment;
import com.blindcats.lifecourse.entity.AchievmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievmentRepository extends JpaRepository<Achievment, Long> {
    List<Achievment> findByAchievmentNameLike(String achievmentName);
    List<Achievment> findByAchievmentTypeLike(AchievmentType achievmentType);
}