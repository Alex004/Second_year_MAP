package entities;

import javafx.util.Pair;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sem1 extends StructuraAnUniversitar {



    public Sem1()
    {
        super.startdatesem1=super.startdatesem1.parse("2019-09-30");
        super.finalsem1=super.finalsem1.parse("2020-01-19");
        addVacanta();
    }

    @Override
    public String getAn() {
        return an_univ;
    }

    @Override
    public LocalDate getStart() {
        return startdatesem1;
    }

    @Override
    public LocalDate getFinal() {
        return super.finalsem1;
    }

    @Override
    public void addVacanta(LocalDate a) {
        for(int i=0;i<super.vacante.size();i++)
        {
            if (((super.vacante.get(i).isBefore(a) || super.vacante.get(i).isEqual(a)) && super.vacante.get(i).plusDays(7).isAfter(a))
                    || ((a.isBefore(super.vacante.get(i)) || a.isEqual(super.vacante.get(i))) && a.plusDays(7).isAfter(super.vacante.get(i))))
            {
                System.out.println("in aceasta saptamana este deja vacanta");
                return;
            }
        }
        super.vacante.add(a);
    }

    @Override
    public void addVacanta() {
        addVacanta(LocalDate.parse("2019-12-23"));
        addVacanta(LocalDate.parse("2019-12-30"));
    }

    @Override
    public int getSizeVacante() {
        return vacante.size();
    }

    @Override
    public LocalDate getPozVacante(int i) {
        return vacante.get(i);
    }


    public void afisV()
    {
        for(int i=0;i<super.vacante.size();i++)
            System.out.println(vacante.get(i));
    }
}
