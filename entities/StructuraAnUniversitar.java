package entities;

import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class StructuraAnUniversitar extends Entity<Long> {
    protected static String an_univ;
    protected LocalDate startdatesem1,finalsem1;
    protected LocalDate  startdatesem2,finalsem2;
    protected ArrayList<LocalDate> vacante=new ArrayList<LocalDate>();
    public abstract String getAn();
    public abstract LocalDate getStart();
    public abstract LocalDate getFinal();
    public abstract void addVacanta(LocalDate a);
    public abstract void addVacanta();
    public abstract int getSizeVacante();
    public abstract LocalDate getPozVacante(int i);




}
