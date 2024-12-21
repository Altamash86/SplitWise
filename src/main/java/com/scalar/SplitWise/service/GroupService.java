package com.scalar.SplitWise.service;

import com.scalar.SplitWise.dto.TransactionDTO;
import com.scalar.SplitWise.exception.GroupNotFoundException;
import com.scalar.SplitWise.model.Group;
import com.scalar.SplitWise.repository.GroupRepository;
import com.scalar.SplitWise.service.startegy.SettleUpStrategyFactory;
import com.scalar.SplitWise.service.startegy.TransactionSettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId);

    @Service
    class Default implements GroupService{

        @Autowired
        GroupRepository groupRepository;

        @Override
        public List<TransactionDTO> settleUpByGroupId(int groupId) {
            Optional<Group> group = groupRepository.findById(groupId);
            if(group.isEmpty()){
                throw new GroupNotFoundException("Group for the given id was not found. Id : " + groupId);
            }
            TransactionSettleUpStrategy settleUpStrategy = SettleUpStrategyFactory.getSettleUpStrategy();
            var expenses = group.get().getExpenses();
            return settleUpStrategy.settleUp(expenses);
        }
    }
}
