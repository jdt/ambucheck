package be.toron.jdt.ambucheck;

public class AmbuCheckException extends Error
{
    public AmbuCheckException(String msg)
    {
        super(msg);
    }

    public AmbuCheckException(Exception e)
    {
        super("An unexpected exception occurred", e);
    }
}
