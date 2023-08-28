package com.example.splitwise.commands;

import com.example.splitwise.controllers.SettleUpController;
import com.example.splitwise.dtos.SettleUpGroupRequestDto;
import com.example.splitwise.dtos.SettleUpGroupResponseDto;
import com.example.splitwise.dtos.SettleUpUserRequestDto;
import com.example.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SettleUpGroup implements Command{
    /*
    Expected Input : SettleUpGroup <group_id>
    */
    private final SettleUpController settleUpController;
    @Autowired
    public SettleUpGroup(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }
    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        SettleUpGroupRequestDto settleUpGroupRequestDto = new SettleUpGroupRequestDto();
        settleUpGroupRequestDto.setGroupId(Long.valueOf(words.get(1)));

        SettleUpGroupResponseDto responseDto = settleUpController.settleUpGroup(settleUpGroupRequestDto);
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return words.size() == 2 && words.get(0).equals(CommandKeywords.settleUpGroup);
    }
}
