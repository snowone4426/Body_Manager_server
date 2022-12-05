package net.ict.bodymanager.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.entity.QMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch {

    public MemberSearchImpl(){
        super(Member.class);
    }

    @Override
    public Page<Member> search1(Pageable pageable) {

        QMember member = QMember.member;

        JPQLQuery<Member> query = from(member);

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(member.email.contains("11")); // title like ...

        booleanBuilder.or(member.gender.contains("11")); // content like ....

        query.where(booleanBuilder);
        query.where(member.member_id.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Member> list = query.fetch();

        long count = query.fetchCount();


        return null;

    }

    @Override
    public Page<Member> searchAll(String[] types, String keyword, Pageable pageable) {

        QMember member = QMember.member;
        JPQLQuery<Member> query = from(member);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "e":
                        booleanBuilder.or(member.email.contains(keyword));
                        break;
                    case "g":
                        booleanBuilder.or(member.gender.contains(keyword));
                        break;
                    case "n":
                        booleanBuilder.or(member.name.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(member.member_id.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Member> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }

}

















