package Interfaces;

import Entities.user;
import javafx.collections.ObservableList;

public interface IServiceRegister {
    public void Register(user u);
    public void updateprofile(user u, user u1);
    public ObservableList<user> showUsers();
}
