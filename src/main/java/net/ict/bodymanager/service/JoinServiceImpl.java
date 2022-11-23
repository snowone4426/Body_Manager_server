package net.ict.bodymanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.repository.JoinRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class JoinServiceImpl implements JoinService{
  private final ModelMapper modelMapper;
  private final JoinRepository joinRepository;


  @Override
  public void register(Long member_id) {
   // MemberEntity memberEntity = JoinRepository.map()

  }
}
