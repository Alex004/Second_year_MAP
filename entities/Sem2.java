package entities;

import java.time.LocalDate;

public class Sem2 extends StructuraAnUniversitar{

    public Sem2()
    {
        super.startdatesem2=super.startdatesem2.parse("2020-02-24");
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
        return finalsem2;
    }


    @Override
    public void addVacanta(LocalDate a) {
        for(int i=0;i<super.vacante.size();i++)
            if(((super.vacante.get(i).isBefore(a)|| super.vacante.get(i).isEqual(a))&& super.vacante.get(i).plusDays(7).isAfter(a))
                    ||((a.isBefore(super.vacante.get(i))||a.isEqual(super.vacante.get(i))) && a.plusDays(7).isAfter(super.vacante.get(i))))
            {
                System.out.println("in aceasta saptamana este deja vacanta");
                return;
            }
        super.vacante.add(a);
    }

    @Override
    public void addVacanta() {

    }

    @Override
    public int getSizeVacante() {
        return 0;
    }

    @Override
    public LocalDate getPozVacante(int i) {
        return null;
    }

    public void afisV()
    {
        for(int i=0;i<super.vacante.size();i++)
            System.out.println(vacante.get(i));
    }

}
