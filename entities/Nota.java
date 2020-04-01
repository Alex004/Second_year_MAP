package entities;

import UI.Pair;

import java.time.LocalDate;

public class Nota extends Entity<Pair<Long,Long>> {
    private float nota;
    private int intarziere;
    private Boolean primire=false;
    private LocalDate predare=null;
    private String feedback;
    private String student;
    private Integer grupa;
    private Integer tema;

    private String prof=null;
    //private Pair<Long,Long> id_ts;



    public Nota(){
        nota=0;
        intarziere=0;
        setId(new Pair<Long, Long>(0L,0L));
//        id_ts=new Pair<Long, Long>(0L,0L);
        predare=LocalDate.now();
        feedback="";
        student="";
        grupa=null;
        tema=null;

    }

    public Nota(float nota,LocalDate s,String prof, Tema x,Long y,String fe /*,String stud,Integer gr,Integer tem*/)
    {

        predare=s;
        setId(new Pair<Long, Long>(x.getId(),y));
        //id_ts=new Pair<Long, Long>(x,y);
        this.prof=prof;
        setIntarziere(x);
        this.nota=nota-intarziere;
        primire=true;
        feedback=fe;
//        student=stud;
//        grupa=gr;
//        tema=tem;
    }
    public Nota(float nota,LocalDate s,int intr,String prof,Long x,Long y,String fe /*String stud,Integer gr,Integer tem*/ )
    {

        predare=s;
        setId(new Pair<Long, Long>(x,y));
        //id_ts=new Pair<Long, Long>(x,y);
        this.prof=prof;
        this.intarziere=intr;
        this.nota=nota;
        primire=true;
        feedback=fe;
//        student=stud;
//        grupa=gr;
//        tema=tem;

    }

    public String getFeedback(){return feedback;}
    public String getStudent(){return student;}
    public Integer getGrupa(){return grupa;}
    public Integer getTema(){return tema;}


    public String getProf() {
        return prof;
    }

    //    public void setPair(Long x,Long y)
//    {
//        id_ts=new Pair<Long, Long>(x,y);
//    }
//    public Pair getPair()
//    {
//        return id_ts;
//    }
//
    public Long getKeyPair()
    {
        return getId().getX();
    }


    public Long getValuePair()
    {
        return getId().getY();
    }


    public boolean isPrimire() {
        return primire;
    }

    public void setPrimire(boolean primire) {
        this.primire = primire;
    }


    public float getNota()
    {
        return nota;
    }
    public void setNota(float x)
    {
        nota=x;
    }

    public int getIntarziere() {
        return intarziere;
    }

    public void setIntarziere(Tema x) {
        LocalDate enddate=x.getEnddate();
        int y=0;
        if((predare.isAfter(enddate)||predare.isEqual(enddate))&&(predare.isBefore(enddate.plusDays(14))||predare.isEqual(enddate.plusDays(14))))
        {
            if((predare.isAfter(enddate)||predare.isEqual(enddate))&&(predare.isBefore(enddate.plusDays(7))||predare.isEqual(enddate.plusDays(7))))
                y=1;
            else
                y=2;
        }
        intarziere=y;
    }

    public LocalDate getPredare() {
        return predare;
    }


    @Override
    public String toString() {
        return "Nota{" +
                "id_tema="+getId().getX()+
                ", id_student="+getId().getY()+
                ", nota=" + nota +
                ", intarziere="+ intarziere+
                ", Profesor="+prof+
                "'}";
    }

}
