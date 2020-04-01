package Repository;

import entities.Entity;
import entities.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID,E extends Entity<ID>> implements Repository<ID,E> {
    private Map<ID,E> entities;
    private Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public E save(E entity){
        if(entity == null)
        {
            throw new IllegalArgumentException("Entity is null.");
        }
        validator.validate(entity,"s");
        E oldEntity=entities.get(entity.getId());
        if(oldEntity==null)
        {
           validator.validate(entity,"s");
            entities.put(entity.getId(),entity);
        }
        else
        {
            System.out.println("exista deja o nota pentru acest student");
        }
        return oldEntity;
    }

    @Override
    public E delete(ID id) {
        if(id==null)
        {
            throw new IllegalArgumentException("Key is not exist");
        }
        return entities.remove(id);

    }

    @Override
    public E findOne(ID id) {
        if(id==null)
        {
            throw new IllegalArgumentException("Key is not exist");
        }
        return entities.get(id);

    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E update(E entity) {
        validator.validate(entity,"u");
        if(entities.remove(entity.getId())==null)
            return entity;
        return entities.putIfAbsent(entity.getId(),entity);//adaugare validator
    }
    // data curenta sa fie mai mica ca deadline la tema

    public Map<ID, E> getEntities() {
        return entities;
    }
}