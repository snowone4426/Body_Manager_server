package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying
    @Query(value = "UPDATE Subscribe s SET s.membership_end = :member_end, s.membership_start = :member_start  where s.member.member_id = :mem_id" )
    @Transactional
    void updateMemShip_before(@Param("member_end") LocalDate mem_end, @Param("member_start")LocalDate mem_start, @Param("mem_id") Long mem_id);

    @Modifying
    @Query(value = "UPDATE Subscribe s SET s.membership_end = :member_end where s.member.member_id = :mem_id" )
    @Transactional
    void updateMemShip_after(@Param("member_end") LocalDate mem_end, @Param("mem_id") Long mem_id);

    @Modifying
    @Query(value = "UPDATE Subscribe s SET s.suit_end = :suit_end , s.suit_start = :suit_start where s.member.member_id = :mem_id" )
    @Transactional
    void updateSuit_before(@Param("suit_end") LocalDate suit_end, @Param("suit_start") LocalDate suit_start, @Param("mem_id") Long mem_id);

    @Modifying
    @Query(value = "UPDATE Subscribe s SET s.suit_end = :suit_end where s.member.member_id = :mem_id" )
    @Transactional
    void updateSuit_after(@Param("suit_end") LocalDate suit_end, @Param("mem_id") Long mem_id);




}
