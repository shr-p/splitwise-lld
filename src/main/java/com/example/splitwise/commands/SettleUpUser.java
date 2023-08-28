package com.example.splitwise.commands;

import com.example.splitwise.controllers.SettleUpController;
import com.example.splitwise.dtos.SettleUpUserRequestDto;
import com.example.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUser implements Command{
    /*
    Expected Input : SettleUp <user_id>
    */
    private final SettleUpController settleUpController;
    @Autowired
    public SettleUpUser(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }
    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(Long.valueOf(words.get(1)));

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(settleUpUserRequestDto);
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return words.size() == 2 && words.get(0).equals(CommandKeywords.settleUpUser);
    }
}
