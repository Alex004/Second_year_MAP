package Service;

import Repository.InMemoryRepository;
import Repository.Repository;
import Repository.TemaFileRepo;
import UI.Citire;
import entities.Tema;
import entities.validators.TemaValidator;
import entities.validators.ValidationException;
import entities.validators.Validator;
import event.StudentChangeEvent;
import event.TemaChangeEvent;
import event.TemaEventType;
import observer.Observable;
import observer.Observer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ServiceTema implements Observable<TemaChangeEvent> {

    private Validator val= new TemaValidator();
    private Repository<Long,Tema> repo = new TemaFileRepo(val,"Tema.xml");
    private Citire c=new Citire();

    //
    //  Operatii tema
    //

    private ServiceTema() {
    }

    public static ServiceTema getInstance() {
        return ServiceTema_singleton.INSTANCE;
    }
    private List<Observer<TemaChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<TemaChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<TemaChangeEvent> e) {

    }

    @Override
    public void notifyObservers(TemaChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }


    private static class ServiceTema_singleton {

        private static final ServiceTema INSTANCE = new ServiceTema();
    }

    public Repository<Long,Tema> getRepo() {
        return repo;
    }

//    public void citire_add_tema()
//    {
//        System.out.println("Introduceti semestrul,durata si descrierea temei");
//        Scanner x=new Scanner(System.in);
////        int sem=x.nextInt();
////        int dur=x.nextInt();
////        String des=x.nextLine();
//        adauga_tema(x.nextInt(),x.nextInt(),x.nextLine());
//
//    }
    public void adauga_din_fisier() throws FileNotFoundException {
        Scanner x=c.citire_fisier_tema();
        if(x!=null)
        {
            while(x.hasNextLine())
            {
                String tem=x.nextLine();
                String[] m=tem.split(",");
                Tema t=new Tema(Integer.parseInt(m[0]),Integer.parseInt(m[1]),m[2],m[3]);
                try {

                    repo.save(t);
                }
                catch (ValidationException | IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
        }


        }
    }

    public Tema adauga_tema(Tema t)
    {

        //Tema t=new Tema(c.citire_tema_sem(),c.citire_tema_dur(),c.citire_tema_descriere());
        try {

            Tema tm=repo.save(t);
            if(tm == null) {
                notifyObservers(new TemaChangeEvent(TemaEventType.ADD,tm));
            }
            return tm;
        }
        catch (ValidationException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return null;


    }



    public Tema sterge_tema(Tema t)
    {

        Tema st=repo.delete(t.getId());
        if(st!=null)
        {
            notifyObservers(new TemaChangeEvent(TemaEventType.DELETE, st));
        }
        return st;
    }
    public Tema afis_tema()
    {
        Long x=c.citire_id_tema();
        return (Tema)repo.findOne(x);

    }
    public void afis_all_tema()
    {
        repo.findAll().forEach(System.out::println);
    }
    public Tema update_tema(Tema t)
    {
        Tema tm=t;
        try {

            tm=repo.findOne(t.getId());
            Tema sub=repo.update(t);
            notifyObservers(new TemaChangeEvent(TemaEventType.UPDATE, t, tm));

            System.out.println("se salveaza in repo");
            return sub;

        }
        catch (ValidationException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return tm;
    }
    public Tema create_tema()
    {
        Long id=c.citire_id_tema();
        int sem=c.citire_tema_sem();
        int dur=c.citire_tema_dur();
        String data=c.citire_tema_data();
        String descriere=c.citire_tema_descriere();

        return new Tema(id,sem,dur,data,descriere);
    }
    public Long getTemaId()
    {
        return c.citire_id_tema();
    }
}
