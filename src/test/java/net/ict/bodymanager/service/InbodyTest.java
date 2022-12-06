package net.ict.bodymanager.service;


import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.repository.InbodyRepository;
import net.ict.bodymanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class InbodyTest {

    @Autowired
    private InbodyRepository inbodyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private InbodyService inbodyService;


    @Test
    public void register(){
        InbodyDTO inbodyDTO = new InbodyDTO();
        inbodyService.register(inbodyDTO);
    }


}
