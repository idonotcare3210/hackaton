package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.InstitutionalMembersList;
import com.blindcats.lifecourse.repository.InstitutionalMembersListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionalMembersListService {
    @Autowired
    private InstitutionalMembersListRepository institutionalMembersListRepository;

    public InstitutionalMembersList save(InstitutionalMembersList member) {
        return institutionalMembersListRepository.save(member);
    }

    // Добавьте здесь другие методы, которые вам нужны
}
