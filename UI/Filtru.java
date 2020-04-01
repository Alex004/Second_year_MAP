package UI;

import Repository.InMemoryRepository;
import Repository.Repository;
import entities.Nota;
import entities.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Filtru {

    public void stud_gr(InMemoryRepository rep,Integer st)
    {


        List l=new ArrayList<>();
        rep.findAll().forEach(l::add);

        l.stream()
                .filter(new Predicate<Student>() {
            @Override
            public boolean test(Student stud) {
                return stud.getGrupa().equals(st);
            }
        })
                .forEach(System.out::println);
    }

    public void stud_pred(Repository stud, InMemoryRepository not,Long tem)
    {
        List l=new ArrayList<>();
        not.findAll().forEach(l::add);
//        List b=new ArrayList();
//        stud.findAll().forEach(b::add);

        l.stream()
                .filter(new Predicate<Nota>() {
            @Override
            public boolean test(Nota o) {
                return o.getId().getX()==tem;
            }
        }).forEach(new Consumer<Nota>() {
            @Override
            public void accept(Nota o) {
                System.out.println(stud.findOne(o.getId().getY()));
            }
        });


    }
    public void stud_pred_prof(InMemoryRepository stud, InMemoryRepository not,Long tem,String prof)
    {
        List l=new ArrayList<>();
        not.findAll().forEach(l::add);
//        List b=new ArrayList();
//        stud.findAll().forEach(b::add);

        l.stream()
                .filter(new Predicate<Nota>() {
                    @Override
                    public boolean test(Nota o) {
                        return o.getId().getX()==tem && o.getProf().equals(prof);
                    }
                }).forEach(new Consumer<Nota>() {
            @Override
            public void accept(Nota o) {
                System.out.println(stud.findOne(o.getId().getY()));
            }
        });
    }
    public void stud_note(Repository<Pair<Long,Long>,Nota> not, LocalDate data)
    {
        List<Nota> l=new ArrayList<>();
        not.findAll().forEach(l::add);
//        List b=new ArrayList();
//        stud.findAll().forEach(b::add);
//       LocalDate t=null;
//       t=t.parse(data);
       LocalDate finalT=data;
        l.stream()
                .filter(new Predicate<Nota>() {
                    @Override
                    public boolean test(Nota o) {
                        return o.getPredare().isEqual(finalT)||(finalT.isBefore(o.getPredare())&& finalT.plusDays(7).isAfter(o.getPredare())) ;
                    }
                }).forEach(System.out::println);
    }
}
