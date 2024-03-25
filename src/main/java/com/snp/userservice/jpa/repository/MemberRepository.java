package com.snp.userservice.jpa.repository;

import com.snp.userservice.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByMemId(String memId);

}
