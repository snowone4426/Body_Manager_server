package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {
}
