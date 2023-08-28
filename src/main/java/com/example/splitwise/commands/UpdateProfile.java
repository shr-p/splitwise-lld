package com.example.splitwise.commands;

import org.springframework.stereotype.Component;

@Component
public class UpdateProfile implements Command{
    @Override
    public void execute(String input) {

    }

    @Override
    public boolean matches(String input) {
        return false;
    }
}
