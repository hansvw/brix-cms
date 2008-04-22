package brix.jcr.event.wrapper;

import java.io.InputStream;
import java.util.Calendar;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.ValueFormatException;

public class ValueFactoryWrapper extends BaseWrapper<ValueFactory> implements ValueFactory
{

    private ValueFactoryWrapper(ValueFactory delegate, SessionWrapper session)
    {
        super(delegate, session);
    }

    public static ValueFactory wrap(ValueFactory delegate, SessionWrapper session)
    {
        if (delegate == null)
        {
            return null;
        }
        else
        {
            return new ValueFactoryWrapper(delegate, session);
        }
    }

    public Value createValue(String value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(long value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(double value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(boolean value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(Calendar value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(InputStream value)
    {
        return getDelegate().createValue(value);
    }

    public Value createValue(Node value) throws RepositoryException
    {
        while (value instanceof NodeWrapper)
        {
            value = ((NodeWrapper)value).getDelegate();
        }
        return getDelegate().createValue(value);
    }

    public Value createValue(String value, int type) throws ValueFormatException
    {
        return getDelegate().createValue(value);
    }

}
