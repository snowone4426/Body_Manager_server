package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.OrderListDTO;
import net.ict.bodymanager.entity.*;
import net.ict.bodymanager.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final OrderRepository orderRepository;
    private final PriceRepository priceRepository;
    private final PTMemberRepository ptMemberRepository;
    private final PTInfoRepository ptInfoRepository;
    private final SubscribeRepository subscribeRepository;
    private final PurchaseRepository purchaseRepository;
    private final MemberRepository memberRepository;
    private final CabinetRepository cabinetRepository;

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
        memberIn.put("end-date", memList.get(0).toArray()[0].toString().substring(0, 10));
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

        for (int i = 0; i < priceList.size(); i++) {
            JSONObject priceInfo = new JSONObject();
            priceInfo.put("price_id", priceList.get(i).toArray()[0]);
            priceInfo.put("price_name", priceList.get(i).toArray()[1]);
            priceInfo.put("price_info", priceList.get(i).toArray()[2]);
            priceArr.put(priceInfo);
        }

        for (int i = 0; i < ptInfoList.size(); i++) {
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

    @Override
    public String orderList() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QPurchase purchase = QPurchase.purchase;
        QPrice price = QPrice.price;
        QOrderInfo orderInfo = QOrderInfo.orderInfo;
        QPTInfo ptInfo = QPTInfo.pTInfo;
        QPTMember ptMember = QPTMember.pTMember;

        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", orderInfo.created_at, ConstantImpl.create("%Y-%m-%d"));

        List<Tuple> count = jpaQueryFactory.select(dateFormat, orderInfo.order_id).from(orderInfo).where(orderInfo.member.member_id.eq(4l)).offset(0).limit(10).orderBy(dateFormat.desc()).fetch();

        List<String> dateList = jpaQueryFactory.select(dateFormat).distinct().from(orderInfo).where(orderInfo.member.member_id.eq(4l))
                .orderBy(dateFormat.asc()).fetch();

        List<Tuple> priceOrder = jpaQueryFactory.select(dateFormat, price.price_name, price.price_info, purchase.orderInfo.order_id)
                .from(purchase).join(purchase.orderInfo, orderInfo).join(purchase.price, price)
                .where(orderInfo.member.member_id.eq(4l))
                .fetch();

        List<Tuple> ptOrder = jpaQueryFactory.select(dateFormat, ptMember.pt_total_count, ptInfo.pt_price, purchase.orderInfo.order_id)
                .from(ptMember)
                .join(ptMember.ptInfo, ptInfo)
                .leftJoin(orderInfo).on(ptMember.member.member_id.eq(orderInfo.member.member_id))
                .leftJoin(purchase).on(ptMember.ptInfo.pt_id.eq(purchase.ptInfo.pt_id))
                .where(orderInfo.member.member_id.eq(4l).and(purchase.orderInfo.order_id.eq(orderInfo.order_id)))
                .fetch();

        JSONArray orderArr = null;

        JSONObject orderObj = null;

        JSONObject dateObj = new JSONObject();

        JSONObject data = new JSONObject();

        for (int i = 0; i < dateList.size(); i++) {

            if (LocalDate.parse(dateList.get(i)).isAfter(LocalDate.parse(count.get(9).toArray()[0].toString())) || LocalDate.parse(dateList.get(i)).isEqual(LocalDate.parse(count.get(9).toArray()[0].toString()))) {

                orderArr = new JSONArray();

                for (int j = 0; j < priceOrder.size(); j++) {

                    for (int n = 0; n < count.size(); n++) {
                        if (count.get(n).toArray()[0].toString().equals(dateList.get(i)) && count.get(n).toArray()[1].toString().equals(priceOrder.get(j).toArray()[3].toString())) {
                            orderObj = new JSONObject();
                            orderObj.put("sub_type", priceOrder.get(j).toArray()[1]);
                            orderObj.put("price_info", priceOrder.get(j).toArray()[2]);
                            orderArr.put(orderObj);
                        }
                        dateObj.put(dateList.get(i), orderArr);
                    }
                }

                for (int m = 0; m < ptOrder.size(); m++) {

                    for (int a = 0; a < count.size(); a++) {
                        if (count.get(a).toArray()[0].toString().equals(dateList.get(i)) && count.get(a).toArray()[1].toString().equals(ptOrder.get(m).toArray()[3].toString())) {
                            orderObj = new JSONObject();
                            orderObj.put("sub_type", "pt " + ptOrder.get(m).toArray()[1] + "회");
                            orderObj.put("price_info", ptOrder.get(m).toArray()[2]);
                            orderArr.put(orderObj);
                        }
                        dateObj.put(dateList.get(i), orderArr);
                    }
                }
            }
        }
        data.put("data", dateObj);
        data.put("message", "ok");

        return data.toString();
    }


    @Override
    public void orderRegister(OrderListDTO orderListDTO) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QPurchase qpurchase = QPurchase.purchase;
        QCabinet qcabinet = QCabinet.cabinet;
        QSubscribe qsubscribe = QSubscribe.subscribe;
        QPTMember qptMember = QPTMember.pTMember;

        List<Tuple> mem_end = jpaQueryFactory.select(qsubscribe.membership_end, qsubscribe.member.member_id).from(qsubscribe).where(qsubscribe.member.member_id.eq(2l)).orderBy(qsubscribe.membership_end.desc()).fetch();

        List<Tuple> suit_end = jpaQueryFactory.select(qsubscribe.suit_end, qsubscribe.member.member_id).from(qsubscribe).where(qsubscribe.member.member_id.eq(2l)).orderBy(qsubscribe.suit_end.desc()).fetch();

        List<Tuple> cab = jpaQueryFactory.select(qcabinet.end_date, qcabinet.member.member_id).from(qcabinet).where(qcabinet.member.member_id.eq(2l)).orderBy(qcabinet.end_date.desc()).fetch();

        List<Tuple> ptMem = jpaQueryFactory.select(qptMember.start_date, qptMember.pt_total_count, qptMember.pt_remain_count, qptMember.member.member_id).from(qptMember).where(qptMember.member.member_id.eq(2l)).orderBy(qptMember.start_date.desc()).fetch();

        LocalDate today = LocalDate.now();

        JSONObject order_list = new JSONObject(orderListDTO);
        JSONArray array = (JSONArray) order_list.get("order_list");

        for (int i = 0; i < array.length(); i++) {
            JSONObject result = array.getJSONObject(i);
            String id = result.getString("id");
            String type = result.getString("type");
            String count = result.getString("count");
            String start = result.getString("start");

            OrderListDTO.OrderRequestDTO o = new OrderListDTO.OrderRequestDTO();
            o.setId(id);
            o.setType(type);
            o.setCount(count);
            o.setStart(start);

            // 멤버아이디 로그인 받아오기
            Member member = memberRepository.getById(2l);
            PTInfo ptInfo = ptInfoRepository.getById(Long.valueOf(id));
            Price price = priceRepository.getById(Long.valueOf(id));

            OrderInfo orderInfo = dtoToEntityOrderInfo(o, member);
            orderRepository.save(orderInfo);

            if (type.equals("price")) {

                Purchase purchase = dtoToEntityPurchase(o, ptInfo, orderInfo, price);
                purchaseRepository.save(purchase);

                if (suit_end.isEmpty() && Long.valueOf(id) == 4) {
                    Subscribe subscribe = dtoToEntitySub(o, member);
                    subscribeRepository.save(subscribe);

                } else if (mem_end.isEmpty() && (Long.valueOf(id) != 5 && Long.valueOf(id) != 4)) {
                    Subscribe subscribe = dtoToEntitySub(o, member);
                    subscribeRepository.save(subscribe);

                } else if (cab.isEmpty() && Long.valueOf(id) == 5) {
                    Cabinet cabinet = dtoToEntityCab(o, member);
                    cabinetRepository.save(cabinet);

                } else if ((mem_end.get(0).toArray()[1] == member.getMember_id()) && (Long.valueOf(id) != 5 && Long.valueOf(id) != 4) && mem_end.get(0).toArray()[0] == null) {
                    subscribeRepository.updateMemShip_before(LocalDate.parse(start).plusMonths(Long.parseLong(count)), LocalDate.parse(start), member.getMember_id());

                } else if ((mem_end.get(0).toArray()[1] == member.getMember_id()) && (Long.valueOf(id) != 5 && Long.valueOf(id) != 4) && LocalDate.parse(mem_end.get(0).toArray()[0].toString()).isBefore(today)) {
                    subscribeRepository.updateMemShip_before(LocalDate.parse(start).plusMonths(Long.parseLong(count)), LocalDate.parse(start), member.getMember_id());

                } else if ((mem_end.get(0).toArray()[1] == member.getMember_id()) && (Long.valueOf(id) != 5 && Long.valueOf(id) != 4) && (LocalDate.parse(mem_end.get(0).toArray()[0].toString()).isAfter(today) || LocalDate.parse(mem_end.get(0).toArray()[0].toString()).isEqual(today))) {
                    subscribeRepository.updateMemShip_after(LocalDate.parse(mem_end.get(0).toArray()[0].toString()).plusMonths((Long.parseLong(count))), member.getMember_id());

                } else if ((suit_end.get(0).toArray()[1] == member.getMember_id()) && Long.valueOf(id) != 5 && suit_end.get(0).toArray()[0] == null) {
                    subscribeRepository.updateSuit_before(LocalDate.parse(start).plusMonths(Long.parseLong(count)), LocalDate.parse(start), member.getMember_id());

                } else if ((suit_end.get(0).toArray()[1] == member.getMember_id()) && Long.valueOf(id) != 5 && LocalDate.parse(suit_end.get(0).toArray()[0].toString()).isBefore(today)) {
                    subscribeRepository.updateSuit_before(LocalDate.parse(start).plusMonths(Long.parseLong(count)), LocalDate.parse(start), member.getMember_id());

                } else if ((suit_end.get(0).toArray()[1] == member.getMember_id()) && Long.valueOf(id) != 5 && (LocalDate.parse(suit_end.get(0).toArray()[0].toString()).isAfter(today) || LocalDate.parse(suit_end.get(0).toArray()[0].toString()).isEqual(today))) {
                    subscribeRepository.updateSuit_after(LocalDate.parse(suit_end.get(0).toArray()[0].toString()).plusMonths((Long.parseLong(count))), member.getMember_id());

                } else if ((cab.get(0).toArray()[1] == member.getMember_id()) && Long.valueOf(id) == 5 && LocalDate.parse(cab.get(0).toArray()[0].toString()).isBefore(today)) {
                    cabinetRepository.updateCab_before(LocalDate.parse(start).plusMonths(Long.parseLong(count)), LocalDate.parse(start), member.getMember_id());

                } else if ((cab.get(0).toArray()[1] == member.getMember_id()) && Long.valueOf(id) == 5 && (LocalDate.parse(cab.get(0).toArray()[0].toString()).isAfter(today) || LocalDate.parse(cab.get(0).toArray()[0].toString()).isEqual(today))) {
                    cabinetRepository.updateCab_after(LocalDate.parse(cab.get(0).toArray()[0].toString()).plusMonths((Long.parseLong(count))), member.getMember_id());
                }

            } else if (type.equals("pt")) {

                Purchase purchase = dtoToEntityPurchase(o, ptInfo, orderInfo, price);
                purchaseRepository.save(purchase);

                if (ptMem.isEmpty()) {
                    PTMember ptMember = dtoToEntityPTMember(o, member, ptInfo);
                    ptMemberRepository.save(ptMember);

                } else if (ptMem.get(0).toArray()[3] == member.getMember_id() && ptMem.get(0).toArray()[2].toString().equals("0")) {
                    ptMemberRepository.updatePt_before(LocalDate.parse(start), Integer.parseInt(count), Integer.parseInt(count), member.getMember_id());

                } else if (ptMem.get(0).toArray()[3] == member.getMember_id() && ptMem.get(0).toArray()[2].toString() != "0") {
                    int count_result = Integer.parseInt(ptMem.get(0).toArray()[2].toString()) + Integer.parseInt(count);
                    ptMemberRepository.updatePt_after(count_result, count_result, member.getMember_id());
                }

            }

        }


    }
}