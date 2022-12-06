package net.ict.bodymanager.service;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import net.ict.bodymanager.repository.MemberRepository;
=======
import net.ict.bodymanager.dto.MemberDTO;
import net.ict.bodymanager.dto.MemberListAllDTO;
import net.ict.bodymanager.dto.PageRequestDTO;
import net.ict.bodymanager.dto.PageResponseDTO;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> origin/feat/mail
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

<<<<<<< HEAD

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
  }
=======
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService,MemberService {
  private final ModelMapper modelMapper;
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
  }
  @Override
  public Long register(MemberDTO memberDTO) {

    Member member = dtoToEntity(memberDTO);

    Long member_id = memberRepository.save(member).getMember_id();

    return member_id;
  }
  @Override
  public MemberDTO readOne(Long member_id) {

    //board_image까지 조인 처리되는 findByWithImages()를 이용
    Optional<Member> result = memberRepository.findByIdWithImages(member_id);

    Member member = result.orElseThrow();

    MemberDTO memberDTO = entityToDTO(member);

    return memberDTO;
  }
  @Override
  public void modify(MemberDTO memberDTO) {

    Optional<Member> result = memberRepository.findById(memberDTO.getMember_id());

    Member member = result.orElseThrow();

    member.change(memberDTO.getEmail(), memberDTO.getGender());

    //첨부파일의 처리
    member.clearImages();

    if(memberDTO.getFileNames() != null){
      for (String fileName : memberDTO.getFileNames()) {
        String[] arr = fileName.split("_");
        member.addImage(arr[0], arr[1]);
      }
    }

    memberRepository.save(member);

  }

  @Override
  public void remove(Long bno) {

    memberRepository.deleteById(bno);

  }


  @Override
  public PageResponseDTO<MemberDTO> list(PageRequestDTO pageRequestDTO) {

    String[] types = pageRequestDTO.getTypes();
    String keyword = pageRequestDTO.getKeyword();
    Pageable pageable = pageRequestDTO.getPageable("member_id");

    Page<Member> result = memberRepository.searchAll(types, keyword, pageable);

    List<MemberDTO> dtoList = result.getContent().stream()
            .map(member -> modelMapper.map(member,MemberDTO.class)).collect(Collectors.toList());


    return PageResponseDTO.<MemberDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total((int)result.getTotalElements())
            .build();

  }












>>>>>>> origin/feat/mail

}
