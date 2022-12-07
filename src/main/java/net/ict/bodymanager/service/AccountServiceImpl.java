package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.entity.*;
import net.ict.bodymanager.repository.AccountRepository;
import org.json.JSONArray;
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

    @Override
    public String infoList() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QPrice price = QPrice.price;
        QPTInfo ptInfo = QPTInfo.pTInfo;
        QMember member = QMember.member;

        List<Tuple> priceList = jpaQueryFactory.select(price.price_id, price.price_name, price.price_info).from(price).where(price.price_id.goe(1l)).fetch();

        // 멤버타입
        List<Tuple> ptInfoList = jpaQueryFactory.select(ptInfo.pt_id, member.name, ptInfo.pt_price).from(ptInfo).leftJoin(ptInfo.trainer, member).on(ptInfo.pt_id.goe(1l)).fetch();

        JSONArray priceArr = new JSONArray();
        JSONArray ptArr = new JSONArray();

        for (int i = 0; i < priceList.size(); i++){
            JSONObject priceInfo = new JSONObject();
            priceInfo.put("price_id", priceList.get(i).toArray()[0]);
            priceInfo.put("price_name", priceList.get(i).toArray()[1]);
            priceInfo.put("price_info", priceList.get(i).toArray()[2]);
            priceArr.put(priceInfo);
        }

        for (int i = 0; i < ptInfoList.size(); i++){
            JSONObject pt_Info = new JSONObject();
            pt_Info.put("pt_id", ptInfoList.get(i).toArray()[0]);
            pt_Info.put("trainer_name", ptInfoList.get(i).toArray()[1]);
            pt_Info.put("pt_price", ptInfoList.get(i).toArray()[2]);
            ptArr.put(pt_Info);
        }

        JSONObject valueArr = new JSONObject();
        valueArr.put("price", priceArr);
        valueArr.put("PT", ptArr);

        JSONObject data = new JSONObject();
        data.put("message", "ok");
        data.put("data", valueArr);
        return data.toString();
    }



}
