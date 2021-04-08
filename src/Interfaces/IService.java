package Interfaces;

import java.util.ArrayList;

public interface IService<T> {
  public void add(T entity);

  public ArrayList<T> getAll();
}

