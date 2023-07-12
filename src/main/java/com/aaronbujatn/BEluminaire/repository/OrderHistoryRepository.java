package com.aaronbujatn.BEluminaire.repository;


import com.aaronbujatn.BEluminaire.model.OrderHistory;
import com.aaronbujatn.BEluminaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {


    List<OrderHistory> findByUser(User user);

}
