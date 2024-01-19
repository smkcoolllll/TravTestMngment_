package com.NymblE.task.Repository;

import com.NymblE.task.Model.PassengerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerActivityRepository extends JpaRepository <PassengerActivity,Long>{
}
