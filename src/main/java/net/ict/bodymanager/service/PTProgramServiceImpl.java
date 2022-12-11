package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.controller.TokenHandler;
import net.ict.bodymanager.entity.QMember;
import net.ict.bodymanager.entity.QPTMember;
import net.ict.bodymanager.entity.QPTProgram;
import net.ict.bodymanager.repository.PTProgramRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class PTProgramServiceImpl implements  PTProgramService{

    private final ModelMapper modelMapper;
    private final PTProgramRepository PTProgramRepository;
    private final TokenHandler tokenHandler;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public String readlist(String date) {
        Long member_id = tokenHandler.getIdFromToken();

        QPTProgram program = QPTProgram.pTProgram;
        QPTMember ptMember = QPTMember.pTMember;
        QMember member = QMember.member;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", program.pt_date, ConstantImpl.create("%Y-%m-%d"));
        List<Tuple> readlist = queryFactory
                .select(program.title,program.weight,program.count)
                .from(program)
                .join(program.ptMember,ptMember)
                .leftJoin(member).on(ptMember.member.member_id.eq(member.member_id))
                .where(dateFormat.eq(date).and(ptMember.member.member_id.eq(member_id)))
                .fetch();
//select title, weight, count, date_format(created_at,'%Y-%m-%d') as date
//from ptprogram join ptmember p on p.pt_member_id = ptprogram.pt_member_id
//    join member m on m.member_id = p.member_id
//where date_format(created_at,'%Y-%m-%d')='2022-12-01' && m.member_id=2
//;

        JSONObject object = null;
        JSONArray array = new JSONArray();

        for(int i=0;i<readlist.size();i++){
            object = new JSONObject();
            object.put("title",readlist.get(i).toArray()[0].toString());
            object.put("weight",readlist.get(i).toArray()[1].toString());
            object.put("count",readlist.get(i).toArray()[2].toString());

            array.put(object);
        }
//        log.info(readlist);
//        log.info(array);

        JSONObject data = new JSONObject();

        data.put("data",array);
        data.put("message","ok");

        log.info(data);
        return data.toString();

    }

    @Override
    public String readmonth(String year, String month) {

        Long member_id = tokenHandler.getIdFromToken();
        
        QPTProgram program = QPTProgram.pTProgram;
        QPTMember ptMember = QPTMember.pTMember;
        QMember member = QMember.member;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", program.pt_date, ConstantImpl.create("%Y-%m-%d"));
        List<String> readmonth = queryFactory
                .select(dateFormat).distinct()
                .from(program)
                .join(program.ptMember,ptMember)
                .leftJoin(member).on(ptMember.member.member_id.eq(member.member_id))
                .where(dateFormat.substring(0,4).eq(year).and(dateFormat.substring(5,7).eq(month)).and(ptMember.member.member_id.eq(member_id)))
                .fetch();

        log.info(readmonth);


        JSONArray array = new JSONArray();

        for(int i=0;i<readmonth.size();i++) {
            array.put(readmonth.get(i));
        }
        JSONObject data = new JSONObject();
        data.put("data",array);
        data.put("message","ok");

        log.info(array);
        log.info(data);

        return data.toString();
    }
//    select distinct date_format(created_at,'%Y-%m-%d') from ptprogram
//    where year(created_at)=2022 && month(created_at)=11;
}
