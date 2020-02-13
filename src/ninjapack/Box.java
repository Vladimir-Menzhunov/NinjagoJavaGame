package ninjapack;

public enum Box
{
    ONE,
    TWO,
    ONENOGO,
    TWONOGO,
    OPENGO,
    EMPTNOGO,
    EMPT,
    CLOSED;


    public Object image;
    public Box getNextNumberBox()
    {
        return Box.values()[this.ordinal() + 1];
    }

    public int getNumber() {
        return this.ordinal();
    }
}
