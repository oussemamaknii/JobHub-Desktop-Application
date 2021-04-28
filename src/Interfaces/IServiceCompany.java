package Interfaces;

import Entities.company;
import javafx.collections.ObservableList;

public interface IServiceCompany {
    public void AddCompany(company comp);
    public void updateCompany(company comp);
    public boolean deleteCompany(int userId);
    public ObservableList<company> showCompanies();
}
