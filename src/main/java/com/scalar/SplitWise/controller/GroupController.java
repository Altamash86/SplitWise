package com.scalar.SplitWise.controller;

import com.scalar.SplitWise.dto.TransactionDTO;
import com.scalar.SplitWise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/settleUp/{groupId}")
    public ResponseEntity settleUp(@PathVariable("groupId") int groupId){
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId);
        return ResponseEntity.ok(transactions);
    }
}
