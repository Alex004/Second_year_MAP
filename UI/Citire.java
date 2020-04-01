package UI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class Citire {
    public String citire_add_student()
    {
        System.out.println("Introduceti numele");
        Scanner x=new Scanner(System.in);
        return x.nextLine();
    }
    public Long citire_id_student()
    {
        System.out.println("Introduceti id_ul studentului");
        Scanner x=new Scanner(System.in);
        return x.nextLong();
    }
    public int citire_tema_sem()
    {
        System.out.println("Introduceti semestrul");
        Scanner x=new Scanner(System.in);
        return x.nextInt();
    }
    public int citire_tema_dur()
    {
        System.out.println("Introduceti durata temei");
        Scanner x=new Scanner(System.in);
        return x.nextInt();
    }
    public String citire_tema_descriere()
    {
        System.out.println("Introduceti descrierea temei");
        Scanner x=new Scanner(System.in);
        return x.nextLine();
    }
    public Long citire_id_tema()
    {
        System.out.println("Introduceti id_ul temei ");
        Scanner x=new Scanner(System.in);
        return x.nextLong();
    }
    public float citire_nota()
    {
        System.out.println("Introduceti nota");
        Scanner x=new Scanner(System.in);
        return x.nextFloat();

    }
    public Scanner citire_fisier_student() throws FileNotFoundException {
        try{
            Scanner x=new Scanner(new FileReader("C:\\Users\\punga\\Desktop\\lab_4\\lab_4\\seminar2\\data\\students.txt"));
            return x;
        }catch(FileNotFoundException e)
        {
            System.out.println("Nu exista fisierul de studenti");
        }
        return null;
    }
    public Scanner citire_fisier_tema() throws FileNotFoundException {
        try{
            Scanner x=new Scanner(new FileReader("C:\\Users\\punga\\Desktop\\lab_4\\lab_4\\seminar2\\data\\teme.txt"));
            return x;
        }catch(FileNotFoundException e)
        {
            System.out.println("Nu exista fisierul de teme");
        }
        return null;
    }
    public Scanner citire_fisier_nota() throws FileNotFoundException {
        try{
            Scanner x=new Scanner(new FileReader("C:\\Users\\punga\\Desktop\\lab_4\\lab_4\\seminar2\\data\\nota.txt"));
            Scanner y=x;
            return y;
        }catch(FileNotFoundException e)
        {
            System.out.println("Nu exista fisierul de nota");
        }
        return null;
    }
    public LocalDate predare()
    {
        System.out.println("Tema a fost prezentata azi?");
        Scanner x= new Scanner(System.in);
        if(x.next().compareTo("y")==0)
            return LocalDate.now();
        System.out.println("Cand a fost prezentata?");
        Scanner y=new Scanner(System.in);
        return LocalDate.parse(y.next());

    }
    public Integer citire_gr()
    {
        System.out.println("Introduceti grupa studentului");
        Scanner x= new Scanner(System.in);
        Integer t=null;
        try{
              t=x.nextInt();
        }catch (Exception e)
        {
            System.out.println("Nu ati introdus un numar intreg");



        }
        return t;
    }
    public String nume_prof()
    {
        System.out.println("Introduce numele profesorului");
        Scanner x=new Scanner(System.in);
        return x.next();
    }
    public int citire_sapt()
    {
        System.out.println("Introduce saptamana");
        Scanner x=new Scanner(System.in);
        return x.nextInt();
    }
    public String citire_tema_data()
    {
        System.out.println("Doriti sa modificati data temei?");
        Scanner x=new Scanner(System.in);
        String raspuns=x.next();
        if(raspuns.equals("y"))
        {
            System.out.println("Doriti sa fie data de start data de azi?");
            Scanner x1=new Scanner(System.in);
            String raspuns1=x.next();
            if(raspuns1.equals("y"))
            {
                return LocalDate.now().toString();
            }
            else
            {
                System.out.println("Introduceti data");
                Scanner x2=new Scanner(System.in);
                return x2.next();

            }
        }
        else
        {
            System.out.println("Introduce data initiala");
            Scanner x3=new Scanner(System.in);
            return x3.next();
        }
    }
}
