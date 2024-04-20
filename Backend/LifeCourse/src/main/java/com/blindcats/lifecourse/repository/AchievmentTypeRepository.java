package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Achievment;
import com.blindcats.lifecourse.entity.AchievmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievmentTypeRepository extends JpaRepository<AchievmentType, Long> {
    List<AchievmentType> findByTypeNameLike(String typeName);
    List<AchievmentType> findByMarkLike(Long mark);
}