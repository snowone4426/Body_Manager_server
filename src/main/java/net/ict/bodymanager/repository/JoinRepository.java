package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<Member, Long> {
}
