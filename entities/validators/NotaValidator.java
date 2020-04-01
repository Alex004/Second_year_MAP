package entities.validators;

import entities.Nota;
import javafx.util.Pair;

public class NotaValidator implements Validator<Nota> {

    @Override
    public void validate(Nota entity,String s) throws ValidationException {
        if(entity.getNota()<1||entity.getNota()>10)
            throw new ValidationException("Nota invalida");
        if(entity.getIntarziere()>2)
            throw new ValidationException("Intarzierea este invalida");


        if(entity.getKeyPair()==null || entity.getValuePair()==null)
        {
            throw new ValidationException("Nu s-a asignat cum trebuie nota unui student la o tema");
        }
    }
}
