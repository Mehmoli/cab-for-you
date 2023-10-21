package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
