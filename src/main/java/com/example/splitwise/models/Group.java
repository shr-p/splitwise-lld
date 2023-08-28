package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Group extends  BaseModel{
    private String groupName;

    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> members;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;



}
