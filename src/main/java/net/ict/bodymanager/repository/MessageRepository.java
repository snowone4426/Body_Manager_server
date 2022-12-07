package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {



}
