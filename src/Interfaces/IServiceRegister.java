package Interfaces;

import Entities.user;

import java.util.List;

public interface IServiceRegister {
    public void Register(user u);
    public void updateprofile(user User);
    public List<user> showUsers();
}
