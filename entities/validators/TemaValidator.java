package entities.validators;

import entities.Tema;

import java.time.LocalDate;

public class TemaValidator implements Validator<Tema>{
    @Override
    public void validate(Tema entity,String s) throws ValidationException {

        if(entity.getSem()!=1 && entity.getSem()!=2)
        {
            throw new ValidationException("Semestru invalid");
        }
        if(!((entity.getSdate().isAfter(entity.getStrStart())||entity.getSdate().isEqual(entity.getStrStart()))&& (entity.getSdate().isBefore(entity.getStrEnd())||entity.getSdate().isEqual(entity.getStrEnd()))))
        {
            throw new ValidationException("Data nu se afla in semestru");
        }
        if(entity.getStartdate()<1||entity.getStartdate()>14)
        {
            throw new ValidationException("Nu se poate stabili saptamana de start");
        }
        if(entity.getStartdate()+entity.getDurata()>14|| entity.getStartdate()+entity.getDurata()<=0)
        {
            throw new ValidationException("deadine-ul nu e in timpul sem");
        }
        if(entity.getEnddate()==null)
        {
            throw new ValidationException("nu s-a putut seta data finala, data finala e dupa finalul sem");
        }
        if(s.compareTo("u")==0)
        {
            if(LocalDate.now().isAfter(entity.getEnddate()))
            {
                System.out.println("ceva");
                if(entity.getEnddate()!=null)
                {
                    throw new ValidationException("nu se poate updata deadline-ul");
                }
            }
        }
    }
}