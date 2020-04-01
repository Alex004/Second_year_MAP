package entities;

import java.time.LocalDate;

public class Tema extends Entity<Long> {
    // valid_temaaritle legate de atributele clasei trebuie realizate in clasa validator
    private String descriere;
    private Integer startdate,durata=0,sem;
    private StructuraAnUniversitar str;
    private LocalDate sdate=null,enddate=null;
    private static long id_tema=1;

    @Override
    public String toString() {
        int end=startdate+durata;
        return "descriere='" + descriere + '\'' +
                ", startWeek=" + startdate +
                ", deadlineWeek=" + end;
    }

    public Tema(Long id_tema,int x,int dur,String d)
    {
        descriere=d;
        sem=x;
        if(sem==1)
            str=new Sem1();
        if(sem==2)
            str=new Sem2();

        setSdate(LocalDate.now());
        setDurata(dur);
        setEnddate();
        setId(id_tema);

    }
    public Tema(int x,int dur,String d)
    {
        descriere=d;
        sem=x;
        if(sem==1)
            str=new Sem1();
        if(sem==2)
            str=new Sem2();

        setSdate(LocalDate.now());
        setDurata(dur);
        setEnddate();
        setId(id_tema);
        id_tema++;
    }
    public Tema(int x,int dur,String data,String d)
    {
        descriere=d;
        sem=x;
        if(sem==1)
            str=new Sem1();
        if(sem==2)
            str=new Sem2();

        setSdate(LocalDate.parse(data));
        setDurata(dur);
        setEnddate();
        setId(id_tema);
        id_tema++;
    }
    public Tema(Long id_tema,int x,int dur,String data,String d)
    {
        descriere=d;
        sem=x;
        if(sem==1)
            str=new Sem1();
        if(sem==2)
            str=new Sem2();

        this.id_tema=id_tema;
        setSdate(LocalDate.parse(data));
        setDurata(dur);
        setEnddate();
        setId(this.id_tema);
        this.id_tema++;
    }


    public StructuraAnUniversitar getStr() {
        return str;
    }

    public LocalDate getStrStart(){ return str.getStart();}
    public LocalDate getStrEnd(){ return str.getFinal();}
    public Integer getSem(){return sem;}
    public void setSem(int x){sem=x;}
    public String getDescriere()
    {
        return descriere;
    }
    public void setDescriere(String descriere)
    {
        this.descriere=descriere;
    }

    public int getStartdate()
    {
        return startdate;
    }

    public void setStartdate()
    {
        int y=1;
        LocalDate c=str.getStart();

        while(c.isBefore(sdate) && y<15)
        {
            c=c.plusDays(7);
            y++;
        }
        y--;

        startdate=y;

    }

    public Integer getDurata()
    {
        return durata;
    }

    public void setDurata(int x)
    {

        durata=x;

    }

    public void setSdate(LocalDate x)
    {
        sdate=x;
        setStartdate();
    }
    public LocalDate getSdate()
    {
        return sdate;
    }

    public void setEnddate()
    {
        enddate=getEndDate(sdate,durata);

    }
    public LocalDate getEnddate()
    {
        return enddate;
    }

    public LocalDate getEndDate(LocalDate a,int x)
    {
        LocalDate b=a.plusDays(7*x);

        if(b.isAfter(str.getFinal()))
        {
            System.out.println("durata depaseste finalul sem");
            return null;
        }
        if(str.getSizeVacante()!=0)
            for(int i=0;i<str.getSizeVacante();i++)
            {
                LocalDate c=str.getPozVacante(i);
                if((str.getPozVacante(i).isBefore(b)|| str.getPozVacante(i).isEqual(b))&& str.getPozVacante(i).plusDays(7).isAfter(b))
                {
                    b=b.plusDays(7);
                    System.out.println(b);
                }
            }
        return b;
    }




}
