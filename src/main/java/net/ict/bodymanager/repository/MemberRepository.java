package net.ict.bodymanager.repository;


import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.repository.search.MemberSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberSearch {
  Optional<Member> findByEmail(String email);

  @EntityGraph(attributePaths = {"imageSet"})
  @Query("select m from Member m where m.member_id =:member_id")
  Optional<Member> findByIdWithImages(Long bno);

}