package net.ict.bodymanager.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.entity.Inbody;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.entity.QInbody;
import net.ict.bodymanager.repository.InbodyRepository;
import net.ict.bodymanager.repository.MemberRepository;
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
public class InbodyServiceImpl implements InbodyService {

    private final InbodyRepository inbodyRepository;
    private final MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public void register(InbodyDTO inbodyDTO) {

        // 로그인한 회원 정보 가져오기
        Member member = memberRepository.getById(3l);
        Inbody inbody = dtoTOEntity(inbodyDTO, member);
        inbodyRepository.save(inbody);
    }

    @Override
    public void modify(InbodyDTO inbodyDTO) {
        
        // 로그인한 회원 정보 가져오기
        Member member = memberRepository.getById(3l);

        inbodyRepository.inbodyUpdate(inbodyDTO.getWeight(),inbodyDTO.getSMM(), inbodyDTO.getBFM(), inbodyDTO.getBMI(), inbodyDTO.getPBF(), inbodyDTO.getWHR(), inbodyDTO.getBMR(),
                inbodyDTO.getBody_muscle(), inbodyDTO.getLeft_hand_muscle(), inbodyDTO.getRight_hand_muscle(), inbodyDTO.getLeft_leg_muscle(), inbodyDTO.getRight_leg_muscle(),
                inbodyDTO.getBody_fat(), inbodyDTO.getLeft_hand_fat(), inbodyDTO.getRight_hand_fat(), inbodyDTO.getLeft_leg_fat(), inbodyDTO.getRight_leg_fat(),
                member.getMember_id());
    }

    @Override
    public List<LocalDate> check() {
        Member member = memberRepository.getById(3l);

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QInbody inbody = QInbody.inbody;
        List<LocalDate> checkList = jpaQueryFactory.select(inbody.created_at).from(inbody).where(inbody.member.member_id.eq(member.getMember_id())).fetch();

        return checkList;
    }

}
