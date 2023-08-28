package com.example.splitwise.controllers;

import com.example.splitwise.dtos.SettleUpGroupRequestDto;
import com.example.splitwise.dtos.SettleUpGroupResponseDto;
import com.example.splitwise.dtos.SettleUpUserRequestDto;
import com.example.splitwise.dtos.SettleUpUserResponseDto;
import com.example.splitwise.models.Expense;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class SettleUpController {
    private final UserService userService;

    @Autowired
    public SettleUpController(UserService userService){
        this.userService = userService;
    }
    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto){
        return null;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto settleUpUserRequestDto){
        SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
        responseDto.setExpenses(userService.settleUp(settleUpUserRequestDto.getUserId()));
        return responseDto;
    }
}
