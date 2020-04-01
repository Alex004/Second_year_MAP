package Repository;

import entities.Entity;
import entities.Student;

public interface Repository<ID,E extends Entity<ID>>{
//    default public void save(E entity){
//        entity.getId();
//    }



    E save(E entity);
    E delete(ID id);
    E findOne(ID id);
    Iterable<E> findAll();
    E update(E entity);

}
