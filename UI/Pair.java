package UI;

public class Pair<E,D> {
    private E x;
    private D y;

    public Pair()
    {
        x=null;
        y=null;
    }
    public Pair(E x, D y) {
        this.x = x;
        this.y = y;
    }

    public E getX() {
        return x;
    }

    public void setX(E x) {
        this.x = x;
    }

    public void setY(D y) {
        this.y = y;
    }

    public D getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Pair))
            return false;

        Pair p=(Pair)obj;
        return ( (this.x==(p.getX()))&& (this.y==(p.getY())));
    }

    @Override
    public int hashCode() {
        Long a=31L;
        return (int)(a*(Long) x+(Long)y);

    }
}
