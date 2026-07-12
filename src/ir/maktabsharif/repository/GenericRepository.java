package ir.maktabsharif.repository;

import java.util.List;

public interface GenericRepository<T>{

    Boolean Save(T t);

    T Update(T t);

    boolean Delete(Long id);

    T FindById(Long id);
    
    List<T> FIndAll();

}
