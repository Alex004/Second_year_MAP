package entities;

import Repository.InMemoryRepository;
import entities.validators.NotaValidator;
import entities.validators.ValidationException;
import entities.validators.Validator;

import java.util.Objects;

public class Student extends Entity<Long> {
    private String name;
    private Integer grupa;
    private static long ID=1;



//    public float getNota()
//    {
//        return nota.getNota();
//    }
//    public void setNota(float x)
//    {
//        nota.setNota(x);
//    }

    public Integer getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }


//    public int getIntarziere() {
//        return nota.getIntarziere();
//    }
//
//    public void setIntarziere(int intarziere) {
//        nota.setIntarziere(intarziere);
//    }

    public Student(String name,Integer gr,Long id) {
        this.name = name;
        this.grupa=gr;
        this.ID=id;
        setId(this.ID);
        this.ID++;


    }

    public Student(String name,Integer gr) {
        this.name = name;
        this.grupa=gr;
        setId(ID);
        ID++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name +", "+
                "grupa="+ grupa+
                ", id="+getId()+
                "'}";
    }

    public String getName() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return Float.compare(student.getNota(), getNota()) == 0 &&
//                Objects.equals(name, student.name);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name;
    }

//    public float getMedia() {
//        return getNota();
//    }
//
//    public void setMedia(float media) {
//        setNota(media);
//    }


}
