package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.FoodModifyRequestDTO;
import net.ict.bodymanager.dto.FoodRequestDTO;
import net.ict.bodymanager.entity.*;
import net.ict.bodymanager.repository.FoodRepository;
import net.ict.bodymanager.repository.MemberRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;

    private final MemberRepository memberRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void register(FoodRequestDTO foodRequestDTO) {
        Member member = memberRepository.getById(1l);
        Food food = dtoToEntity(foodRequestDTO,member);
        foodRepository.save(food);

        log.info(foodRepository.save(food));
    }


    @Override
    public void modify(FoodModifyRequestDTO foodDTO) {

        Member member = memberRepository.getById(1l);

        Optional<Food> result = foodRepository.findById(foodDTO.getFood_id());
        Food food = result.orElseThrow();
        food.change(foodDTO.getTime(),foodDTO.getContent());
        food.clearImage();

        List<String> img =  new ArrayList<>();
        img.add(foodDTO.getFood_img());
        if(foodDTO.getFood_img() !=null) {
            img.forEach(fileName -> {
                String[] arr = fileName.split("_");
                food.addImage(arr[0], arr[1]);
            });

            foodRepository.save(food);
        }
    }


    @Override
    public String readOne(String date){
        QFood food = QFood.food;
        QFile file = QFile.file;
        QMember member = QMember.member;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", food.created_at, ConstantImpl.create("%Y-%m-%d"));
        StringTemplate timeFormat = Expressions.stringTemplate("DATE_FORMAT( {0}, {1} )", food.created_at, ConstantImpl.create("%H:%i %p"));

        List<Tuple> readbreakfast = queryFactory
                    .select(food.food_id, file.file_id, food.content, timeFormat, food.grade)
                    .from(food)
                    .join(food.food_img, file)
                    .leftJoin(member).on(food.member.member_id.eq(member.member_id))
                    .where(dateFormat.eq(date).and(food.member.member_id.eq(1l)).and(food.time.eq("아침")))
                    .fetch();

        List<Tuple> readlunch = queryFactory
                .select(food.food_id, file.file_id, food.content, timeFormat, food.grade)
                .from(food)
                .join(food.food_img,file)
                .leftJoin(member).on(food.member.member_id.eq(member.member_id))
                .where(dateFormat.eq(date).and(food.member.member_id.eq(1l)).and(food.time.eq("점심")))
                .fetch();

        List<Tuple> readdinner = queryFactory
                .select(food.food_id, file.file_id, food.content, timeFormat, food.grade)
                .from(food)
                .join(food.food_img,file)
                .leftJoin(member).on(food.member.member_id.eq(member.member_id))
                .where(dateFormat.eq(date).and(food.member.member_id.eq(1l)).and(food.time.eq("저녁")))
                .fetch();

        JSONObject object = null;

        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();

        if(readbreakfast.isEmpty() && readlunch.isEmpty() && readdinner.isEmpty()){
            jsonObject.put("message", "empty");
            jsonObject.put("data",data);

        }
        else {


            for (int i = 0; i < readbreakfast.size(); i++) {
                object = new JSONObject();
                object.put("id", readbreakfast.get(i).toArray()[0]);
                object.put("photo", readbreakfast.get(i).toArray()[1].toString());
                object.put("content", readbreakfast.get(i).toArray()[2].toString());
                object.put("created_at", readbreakfast.get(i).toArray()[3].toString());
                object.put("grade", readbreakfast.get(i).toArray()[4]);

                data.put("breakfast", object);
            }
            for (int i = 0; i < readlunch.size(); i++) {
                object = new JSONObject();
                object.put("id", readlunch.get(i).toArray()[0]);
                object.put("photo", readlunch.get(i).toArray()[1].toString());
                object.put("content", readlunch.get(i).toArray()[2].toString());
                object.put("created_at", readlunch.get(i).toArray()[3].toString());
                object.put("grade", readlunch.get(i).toArray()[4]);

                data.put("lunch", object);
            }
            for (int i = 0; i < readdinner.size(); i++) {
                object = new JSONObject();
                object.put("id", readdinner.get(i).toArray()[0]);
                object.put("photo", readdinner.get(i).toArray()[1].toString());
                object.put("content", readdinner.get(i).toArray()[2].toString());
                object.put("created_at", readdinner.get(i).toArray()[3].toString());
                object.put("grade", readdinner.get(i).toArray()[4]);

                data.put("dinner", object);
            }

            log.info(data);

            jsonObject.put("data", data);
            jsonObject.put("message", "ok");
        }

        log.info(jsonObject);

        return jsonObject.toString();
    }
    //        select f1.time, f1.food_id, f2.file_id  , f1.content, f1.created_at, f1.grade
//        from food f1 join file f2 on f1.food_id = f2.food_id
//        join member m on m.member_id = f1.member_id
//        where time = '점심' && m.member_id=1
//                && date_format(created_at,'%Y-%m-%d')='2022-12-04';
    @Override
    public void remove(Long food_id) {
        foodRepository.deleteById(food_id);
    }



}
