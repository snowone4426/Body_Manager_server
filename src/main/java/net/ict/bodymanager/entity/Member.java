package net.ict.bodymanager.entity;


import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"imageSet","roles"})
public class Member extends BaseEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long member_id;
  @Column(length = 100, nullable = false, unique = true)
  private String email;
  @Column(length = 100, nullable = false)
  private String password;
  @Column(length = 10, nullable = false)
  private String name;
  @Column(length = 100, nullable = false)
  private String address;
  @Column(length = 15, nullable = false)
  private String phone;
  @Column(length = 10, nullable = false)
  private String gender;
  @Column(nullable = false)
  private double height;
  @Column(length = 300, nullable = false)
  private String remark;
  @Column(nullable = false)
  private LocalDate birth;
  @Column(nullable = false)
  @ColumnDefault("0")
  private String type;  //0=user, 1=trainer , 2=admin , 3=Uncertified(소셜로그인미인증), 4=dormant(휴면)

  @OneToMany(mappedBy = "member",
          cascade = {CascadeType.ALL},
          fetch = FetchType.LAZY,
          orphanRemoval = true)
  @Builder.Default
  @BatchSize(size = 20)
  private Set<MemberImage> imageSet = new HashSet<>();


  @ElementCollection(fetch = FetchType.EAGER)
  @Builder.Default
  private List<String> roles = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }

  public void addImage(String uuid, String fileName) {
    MemberImage memberImage = MemberImage.builder()
            .uuid(uuid)
            .fileName(fileName)
            .member(this)
            .ord(imageSet.size())
            .build();
    imageSet.add(memberImage);
  }

  public void change(String email, String gender) {
    this.email = email;
    this.gender = gender;
  }

  public void clearImages() {
    imageSet.forEach(memberImage -> memberImage.changeBoard(null));
    this.imageSet.clear();
  }

  public void changePassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
