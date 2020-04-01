package entities.validators;

import entities.Student;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

public class StudentValidator implements Validator<Student> {


    @Override
    public void validate(Student entity,String s) throws ValidationException {
       // throw new ValidationException("Student invalid.");
        String g=entity.getName();
        for(int i=0;i<g.length();i++)
            if(g.charAt(i)!=' '&&!(g.charAt(i)>='a'&& g.charAt(i)<='z')&&!(g.charAt(i)>='A'&&g.charAt(i)<='Z'))
            {
                throw new ValidationException("Nume student invalid");
            }
        if(entity.getGrupa()==null )
        {
            throw new ValidationException("Valoare invalida a grupei");
        }

    }
}
