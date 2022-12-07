package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.PTMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface PTMemberRepository extends JpaRepository<PTMember, Long> {

    @Modifying
    @Query(value = "UPDATE PTMember p SET p.start_date = :start_date , p.pt_total_count = :total_count, p.pt_remain_count = :remain_count where p.member.member_id = :mem_id" )
    @Transactional
    void updatePt_before(@Param("start_date") LocalDate start_date, @Param("total_count") int total_count, @Param("remain_count") int remain_count, @Param("mem_id") Long mem_id);

    @Modifying
    @Query(value = "UPDATE PTMember p SET p.pt_total_count = :total_count, p.pt_remain_count = :remain_count where p.member.member_id = :mem_id" )
    @Transactional
    void updatePt_after(@Param("total_count") int total_count, @Param("remain_count") int remain_count, @Param("mem_id") Long mem_id);


}
