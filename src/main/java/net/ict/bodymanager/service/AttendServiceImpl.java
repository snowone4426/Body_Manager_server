package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.AttendDTO;
import net.ict.bodymanager.entity.Attend;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.entity.QAttend;
import net.ict.bodymanager.repository.AttendRepository;
import net.ict.bodymanager.repository.MemberRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.ict.bodymanager.entity.QAttend.attend;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AttendServiceImpl implements AttendService {
    private final ModelMapper modelMapper;
    private final AttendRepository attendRepository;
    private final MemberRepository memberRepository;

    @PersistenceContext
    EntityManager entityManager;


//    @Override
//    public void register(AttendDTO attendDTO) {

    @Override
    public void register(AttendDTO attendDTO) {

        Member member = memberRepository.getById(1l);

        QAttend qattend = QAttend.attend;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        List<LocalDateTime> select = queryFactory
                .select(qattend.end_date)
                .from(attend)
                .orderBy(qattend.start_date.desc())
                .where(qattend.end_date.isNull().and(qattend.member.member_id.eq((member.getMember_id()))))
                .limit(1)
                .fetch();
        log.info("===================================="+select);

        if(select.isEmpty()) {
            Attend attend = dtoToEntity(attendDTO, member);
            attendRepository.save(attend);
        }
        else {
            attendRepository.updateDate(LocalDateTime.now(), member.getMember_id());
        }

    }
//    @Override
//    public void modify() {
//        Member member = memberRepository.getById(3l);
//
////        LocalDate date = LocalDate.now();
////        LocalTime time = LocalTime.of(00, 00, 00);
////
////
////        LocalDateTime today = LocalDateTime.of(date, time);
//
////        attendRepository.updateDate(LocalDateTime.now(), member.getMember_id(), today);
//        attendRepository.updateDate(LocalDateTime.now(), member.getMember_id());
//
//    }


    @Override
    public String readDay() {

        QAttend attend = QAttend.attend;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        StringTemplate startdateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", attend.start_date, ConstantImpl.create("%Y-%m-%d"));
        StringTemplate enddateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", attend.end_date, ConstantImpl.create("%Y-%m-%d"));

        List<Tuple> readday = queryFactory
                .select(startdateFormat, enddateFormat)
                .from(attend)
                .where(startdateFormat.eq(String.valueOf(LocalDate.now())).and(attend.member.member_id.eq(3l)))
                .fetch();
        log.info(readday);

        JSONObject object = null;
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<readday.size();i++){
            object = new JSONObject();
            object.put("start_time", readday.get(i).toArray()[0].toString());
            object.put("end_time", readday.get(i).toArray()[1].toString());

            jsonArray.put(object);
        }

        JSONObject data = new JSONObject();
        data.put("data", object);
        data.put("message","ok");

        return data.toString();
//        Member member = memberRepository.getById(3l);
//        attendRepository.list(attend.start_date,member.getMember_id());
//
//        for(int i=0;i<attendRepository.list().size();i++) {
//            object =  new JSONObject();
//
//           if(attendRepository.list().get(i).getEnd_date()!=null) {
//               object.put("start_date", attendRepository.list().get(i).getStart_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//               object.put("end_date", attendRepository.list().get(i).getEnd_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//           }
//           else{
//               object.put("start_date", attendRepository.list().get(i).getStart_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//               object.put("end_date", "");
//            }
//
//            jsonArray.put(object);
//
//        }
//        JSONObject data = new JSONObject();
//        data.put("data", jsonArray);
//        data.put("message", "ok");
//        return data.toString();
    }



    @Override
    public String readMonth(String year, String month) {

        QAttend attend = QAttend.attend;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//        select date_format (start_date,'%Y-%m-%d') as date ,count(*) from attend where year(start_date)=2022 and month(start_date) = 12 and member_id=1 group by date_format (start_date,'%Y-%m-%d' ) ;
        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", attend.end_date, ConstantImpl.create("%Y-%m-%d"));

        List<Tuple> readmonth = queryFactory
                .select(dateFormat, dateFormat.count())
                .from(attend)
                .where(dateFormat.substring(0,4).eq(year).and(dateFormat.substring(5,7).eq(month)).and(attend.member.member_id.eq(3L)))
                .groupBy(dateFormat)
                .fetch();

        log.info(readmonth);

        JSONObject object = null;;
        object = new JSONObject();

        for(int i=0;i<readmonth.size();i++){

            if(attend.start_date !=null) {
                object.put(readmonth.get(i).toArray()[0].toString(), readmonth.get(i).toArray()[1]);
            }

        }
//        data:{1:["2022-12-03"],2:["2022-12-02"]} 이케 가능?
        JSONObject data = new JSONObject();
        data.put("data", object);
        data.put("message","ok");

        return data.toString();
    }
}
