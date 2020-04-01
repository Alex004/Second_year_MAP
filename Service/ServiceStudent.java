package Service;

import Repository.InMemoryRepository;
import Repository.Repository;
import Repository.StudentFileRepository;

import UI.Citire;
import UI.Ui;
import entities.Student;
import entities.validators.StudentValidator;
import entities.validators.ValidationException;
import entities.validators.Validator;
import event.StudentChangeEvent;
import event.StudentEventType;
import observer.Observable;
import observer.Observer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import event.StudentChangeEvent;

public class ServiceStudent implements Observable<StudentChangeEvent> {

    private Validator val = new StudentValidator();
    private Repository<Long,Student> repo = new StudentFileRepository(val,"/view/Student.xml");
    private Citire c=new Citire();


    public Repository<Long,Student> getRepo() {
        return repo;
    }
    private ServiceStudent() {
    }

    public static ServiceStudent getInstance() {
        return ServiceStudent_singleton.INSTANCE;
    }

    private List<Observer<StudentChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<StudentChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<StudentChangeEvent> e) {

    }

    @Override
    public void notifyObservers(StudentChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }


    private static class ServiceStudent_singleton {

        private static final ServiceStudent INSTANCE = new ServiceStudent();
    }
    //
    //  Operatii student
    //


    public void adauga_din_fisier() throws FileNotFoundException {
        Scanner x=c.citire_fisier_student();
        if(x!=null)
        {
            while(x.hasNextLine())
            {
                String stud=x.nextLine();
                String[] s=stud.split(",");
                Student student=new Student(s[0],Integer.parseInt(s[1]));
                try {

                    repo.save(student);
                }
                catch (ValidationException | IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }

            }
        }


    }

    public Student adauga_student( Student stude)
    {
        //String nume=c.citire_add_student();
        //Student stud=new Student(nume,c.citire_gr());
        try {

            Student s= repo.save(stude);
            if(s == null) {
                notifyObservers(new StudentChangeEvent(StudentEventType.ADD,s));
            }

        }
        catch (ValidationException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Student sterge_student(Student stud)
    {
        //Long x=c.citire_id_student();

        Student st=repo.delete(stud.getId());
        if(st!=null)
        {
            notifyObservers(new StudentChangeEvent(StudentEventType.DELETE, st));
        }
        return st;
    }
    public Student afis_student()
    {
        Long x=c.citire_id_student();
        return (Student) repo.findOne(x);
    }

    public void afis_all_studenti()
    {
        repo.findAll().forEach(System.out::println);
    }
    public Student  update_student(Student stud)
    {
        Student st=stud;

        try {

            st=repo.findOne(stud.getId());
            Student sub=repo.update(stud);
            notifyObservers(new StudentChangeEvent(StudentEventType.UPDATE, stud, st));

            System.out.println("se salveaza in repo");
            return sub;
        }
        catch (ValidationException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return st;
    }
    public Student create_student()
    {
        String name=c.citire_add_student();
        Long id=c.citire_id_student();
        Integer grupa=c.citire_gr();

        return new Student(name,grupa,id);

    }
    public Integer getGrupa()
    {
        return c.citire_gr();
    }
}
