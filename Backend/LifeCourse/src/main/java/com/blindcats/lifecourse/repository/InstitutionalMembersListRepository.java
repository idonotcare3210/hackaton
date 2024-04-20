package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.InstitutionRole;
import com.blindcats.lifecourse.entity.InstitutionalMembersList;
import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionalMembersListRepository extends JpaRepository<InstitutionalMembersList, Long> {
    List<InstitutionalMembersList> findByInstitutionLike(Institution institution);
    InstitutionalMembersList findByUser(User user);
    List<InstitutionalMembersList> findByInstitutionRoleLike(InstitutionRole institutionRole);
}