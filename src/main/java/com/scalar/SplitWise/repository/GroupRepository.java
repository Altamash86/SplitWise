package com.scalar.SplitWise.repository;

import com.scalar.SplitWise.model.Group;
import com.scalar.SplitWise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
