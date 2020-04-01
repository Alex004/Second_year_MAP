package UI;

import Repository.InMemoryRepository;
import Repository.NotaFileRepo;
import Repository.StudentFileRepository;
import Service.ServiceNota;
import Service.ServiceStudent;
import Service.ServiceTema;
import entities.Student;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    private ServiceStudent ss=ServiceStudent.getInstance();
    private ServiceTema st=ServiceTema.getInstance();
    private ServiceNota sn=ServiceNota.getInstance();
    private Filtru f=new Filtru();


    private static Ui instance;
    private Ui() {

    }


    public  static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }
    public void cmd(String comanda) throws FileNotFoundException {
        while(comanda.compareTo("exit")!=0){
            switch (comanda) {

                case "1":{
                    ss.adauga_din_fisier();
                    break;
                }
                case "2": {
                   // ss.adauga_student();
                    break;

                }
                case "3": {
//                    ss.sterge_student();
                    break;
                }
                case "4": {
                    System.out.println(ss.afis_student());
                    break;
                }
                case "5": {
                    ss.afis_all_studenti();
                    break;
                }
                case "6": {
                    ss.update_student(ss.create_student());
                    break;
                }
                case "7":{
                    st.adauga_din_fisier();
                    break;
                }
                case "8": {
                  //  st.adauga_tema();
                    break;
                }
                case "9": {
                    //st.sterge_tema();
                    break;
                }
                case "10": {
                    System.out.println(st.afis_tema());
                    break;
                }
                case "11": {
                    st.afis_all_tema();
                    break;
                }
                case "12": {
                    st.update_tema(st.create_tema());
                    break;
                }
                case "13":{
                    sn.adauga_din_fisier();
                    break;
                }
//                case "14": {
//                    sn.adauga_nota();
//                    break;
//                }
//                case "15": {
//                    sn.sterge_nota();
//                    break;
//                }
                case "16": {
                    System.out.println(sn.afis_nota());
                    break;
                }
                case "17": {
                    sn.afis_all_nota();
                    break;
                }
//                case "18": {
//                    sn.update_nota(sn.create_nota());
//                    break;
                //}
                case"19":{
                    f.stud_gr((StudentFileRepository) ss.getRepo(),ss.getGrupa());
                    break;
                }
                case "20":{
                    f.stud_pred((StudentFileRepository) ss.getRepo(),(NotaFileRepo)sn.getRepo(),st.getTemaId());
                    break;
                }
                case "21":{
                    f.stud_pred_prof((StudentFileRepository) ss.getRepo(),(NotaFileRepo)sn.getRepo(),st.getTemaId(),sn.cit_prof());
                    break;
                }
                case "22":
                {
                    f.stud_note(sn.getRepo(),sn.calc_start_sapt(sn.cit_sapt()));
                    break;
                }

                case "23":{
                    System.out.println("1: Adaugare din fisier a studentilor");
                    System.out.println("2: Adaugare de la tastatura a studentilor");
                    System.out.println("3: Stergerea unui student");
                    System.out.println("4: Afisarea unui student");
                    System.out.println("5: Afisarea tuturor studentilor");
                    System.out.println("6: Update student");
                    System.out.println("7: Adaugare din fisier a temelor");
                    System.out.println("8: Adaugare de la tastatura a temelor");
                    System.out.println("9: Stergerea unei teme");
                    System.out.println("10: Afisarea unei teme");
                    System.out.println("11: Afisarea tuturor temelor");
                    System.out.println("12: Update tema");
                    System.out.println("13: Adaugare din fisier a notelor");
                    System.out.println("14: Adaugare de la tastatura a notelor");
                    System.out.println("15: Stergerea unei note");
                    System.out.println("16: Afisarea unei note");
                    System.out.println("17: Afisarea tuturor notelor");
                    System.out.println("18: Update nota");
                    System.out.println("19: Filtrare grupa");
                    System.out.println("20: Filtrare predare tema");
                    System.out.println("21: Filtrare predare tema profesor");
                    System.out.println("22: Filtrare nota intr-o anumita saptamana");
                    System.out.println("23: Meniu help ");

                }

            }
            System.out.println();
            System.out.println("Alege comanda:\n");
            Scanner scanner=new Scanner(System.in);
            comanda=scanner.next();
        }

    }
}
