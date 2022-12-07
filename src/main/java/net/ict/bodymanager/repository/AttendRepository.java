package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Long> {


//    @Query(value = "select * from attend\n" +
//            "WHERE date(end_date) >= date(subdate(now(), INTERVAL 1 DAY))\n" +
//            "  And date(end_date) <= date(now()) and member_id =2 order by end_date asc;",nativeQuery = true)
    @Query(value = "select * from attend where to_days(start_date) = to_days(now()) and member_id=2;",nativeQuery = true)

    List<Attend> list() ;

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE Attend a set a.end_date = :end_date where a.member.member_id= :member_id and a.end_date IS NULL and a.start_date >= :today ")
//    void updateDate(@Param(value = "end_date") LocalDateTime end_date, @Param(value = "member_id") Long member_id, @Param(value = "today") LocalDateTime today);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Attend a set a.end_date = :end_date where a.member.member_id= :member_id and a.end_date IS NULL ")
    void updateDate(@Param(value = "end_date") LocalDateTime end_date, @Param(value = "member_id") Long member_id);


}
