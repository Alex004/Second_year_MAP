package Service;

import Repository.InMemoryRepository;
import Repository.Repository;
import Repository.NotaFileRepo;
import UI.Citire;
import UI.Pair;
import entities.*;

import entities.validators.NotaValidator;
import entities.validators.ValidationException;
import entities.validators.Validator;
import event.NotaChangeEvent;
import event.NotaEventType;
import event.TemaChangeEvent;
import event.TemaEventType;
import observer.Observable;
import observer.Observer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceNota implements Observable<NotaChangeEvent> {
    //
    //  Operatii nota
    //
    private Validator val= new NotaValidator();
    private Repository<Pair<Long, Long>, Nota> repo = new NotaFileRepo(val,"view/Nota.xml");
    private ServiceTema st=ServiceTema.getInstance();
    private ServiceStudent ss=ServiceStudent.getInstance();
    private Citire c=new Citire();

    public Repository<Pair<Long, Long>, Nota> getRepo() {
        return repo;
    }

    private ServiceNota() {
    }

    public static ServiceNota getInstance() {
        return ServiceNota.ServiceNota_singleton.INSTANCE;
    }

    private static class ServiceNota_singleton {

        private static final ServiceNota INSTANCE = new ServiceNota();
    }

    public void adauga_din_fisier() throws FileNotFoundException {
        Scanner x=c.citire_fisier_nota();
        if(x!=null)
        {
            while(x.hasNextLine())
            {
                String not=x.nextLine();
                String[] m=not.split(",");
                Nota nota=new Nota(Float.parseFloat(m[0]),LocalDate.parse(m[1]),Integer.parseInt(m[2]),m[3],Long.parseLong(m[4]),Long.parseLong(m[5]),m[6]);
                try {

                    repo.save(nota);
                }
                catch (ValidationException | IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public Nota adauga_nota(Nota t)
    {
        try {

        Nota tm=repo.save(t);
        if(tm == null) {
            notifyObservers(new NotaChangeEvent(NotaEventType.ADD,tm));
        }
        return tm;
    }
        catch (ValidationException | IllegalArgumentException e){
        System.out.println(e.getMessage());
    }
        return null;


    }

    public Nota sterge_nota(Nota t)
    {

        Nota st=repo.delete(t.getId());
        if(st!=null)
        {
            notifyObservers(new NotaChangeEvent(NotaEventType.DELETE, st));
        }
        return st;
    }
    public Nota afis_nota()
    {
        Tema t=st.afis_tema();
        Student s=ss.afis_student();
        Pair<Long,Long> p=new Pair<Long, Long>();
        Long x=t.getId();
        p.setX(x);
        p.setY((Long)s.getId());
        return (Nota)repo.findOne(p);
    }
    public void afis_all_nota()
    {
        repo.findAll().forEach(System.out::println);
    }
    public void update_nota(Nota nota)
    {
        try {
            repo.update(nota);
        }
        catch (ValidationException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
//    public Nota create_nota()
//    {
//        float nota=c.citire_nota();
//        Tema t=st.afis_tema();
//        Student s=ss.afis_student();
//        LocalDate data=c.predare();
//        return new Nota(nota,data,c.nume_prof(),t,s.getId());
//    }

    public LocalDate calc_start_sapt( int x)
    {
        int sem=c.citire_tema_sem();
        StructuraAnUniversitar st=null;
        if(sem==1)
            st=new Sem1();
        else
        if(sem==2)
            st=new Sem2();
        else
        {
            System.out.println("nu se poate");
            return null;
        }
        if(x<0||x>14)
        {
            System.out.println("Ceva nu e bine");
            return null;
        }
        else
        {
            LocalDate b=st.getStart();
            b=b.plusDays(7*x);
            if(b.isAfter(st.getFinal()))

                if(st.getSizeVacante()!=0)
                    for(int i=0;i<st.getSizeVacante();i++)
                    {
                        LocalDate c=st.getPozVacante(i);
                        if((st.getPozVacante(i).isBefore(b)|| st.getPozVacante(i).isEqual(b))&& st.getPozVacante(i).plusDays(7).isAfter(b))
                        {
                            b=b.plusDays(7);
                            System.out.println(b);
                        }
                    }
            return b;

        }

    }
    public String cit_prof()
    {
        return c.nume_prof();
    }
    public int cit_sapt()
    {
        return c.citire_sapt();
    }

    private List<Observer<NotaChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<NotaChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<NotaChangeEvent> e) {

    }

    @Override
    public void notifyObservers(NotaChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
