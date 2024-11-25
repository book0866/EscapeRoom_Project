package com.example.demo.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findById(String id); // 아이디로 회원 조회
	
	
	// id 랜덤 조회 쿼리문 작성 나중에 지우기!
	@Query(value = 
			"SELECT * FROM member ORDER BY RAND() LIMIT 1"
			
			, nativeQuery = true)
    Member findRandomMember();
//끝
}
