package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.entity.QAttend;
import net.ict.bodymanager.entity.QPTMember;
import net.ict.bodymanager.repository.AccountRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    EntityManager entityManager;


    @Override
    public String MemberInfo() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QAttend attend = QAttend.attend;
        QPTMember ptMember = QPTMember.pTMember;

        StringTemplate dataFormat = Expressions.stringTemplate("DATE_FORMAT({0,{1},{2})", attend.end_date);

        List<Tuple> memList = jpaQueryFactory.select(attend.end_date, ptMember.pt_remain_count).from(attend, ptMember).where(attend.member.member_id.eq(1l)).fetch();

        JSONObject memberIn = new JSONObject();
        memberIn.put("end-date", memList.get(0).toArray()[0].toString().substring(0,10));
        memberIn.put("pt-count", memList.get(0).toArray()[1]);

        JSONObject dataMember = new JSONObject();
        dataMember.put("data", memberIn);
        dataMember.put("message", "ok");

        return dataMember.toString();
    }


}
