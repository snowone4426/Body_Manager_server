package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.PTMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PTMemberRepository extends JpaRepository<PTMember, Long> {
}
