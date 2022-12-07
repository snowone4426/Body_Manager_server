package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE Attend a set a.end_date = :end_date where a.member.member_id= :member_id and a.end_date IS NULL ")
    void updateDate(@Param(value = "end_date") LocalDateTime end_date, @Param(value = "member_id") Long member_id);


}
