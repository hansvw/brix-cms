package brix.jcr.event.wrapper;

import java.io.InputStream;
import java.util.Calendar;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.PropertyDefinition;
import javax.jcr.version.VersionException;

class PropertyWrapper extends ItemWrapper implements Property
{

    private PropertyWrapper(Property delegate, SessionWrapper session)
    {
        super(delegate, session);
    }

    public static PropertyWrapper wrap(Property delegate, SessionWrapper session)
    {
        if (delegate == null)
        {
            return null;
        }
        else
        {
            return new PropertyWrapper(delegate, session);
        }
    }

    @Override
    public Property getDelegate()
    {
        return (Property)super.getDelegate();
    }

    public boolean getBoolean() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getBoolean();
    }

    public Calendar getDate() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getDate();
    }

    public PropertyDefinition getDefinition() throws RepositoryException
    {
        return getDelegate().getDefinition();
    }

    public double getDouble() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getDouble();
    }

    public long getLength() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getLength();
    }

    public long[] getLengths() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getLengths();
    }

    public long getLong() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getLong();
    }

    public Node getNode() throws ValueFormatException, RepositoryException
    {
        return NodeWrapper.wrap(getDelegate().getNode(), getSessionWrapper());
    }

    public InputStream getStream() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getStream();
    }

    public String getString() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getString();
    }

    public int getType() throws RepositoryException
    {
        return getDelegate().getType();
    }

    public Value getValue() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getValue();
    }

    public Value[] getValues() throws ValueFormatException, RepositoryException
    {
        return getDelegate().getValues();
    }

    public void setValue(Value value) throws ValueFormatException, VersionException, LockException,
            ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(Value[] values) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(values);
    }

    public void setValue(String value) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(String[] values) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(values);
    }

    public void setValue(InputStream value) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(long value) throws ValueFormatException, VersionException, LockException,
            ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(double value) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(Calendar value) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(boolean value) throws ValueFormatException, VersionException,
            LockException, ConstraintViolationException, RepositoryException
    {
        getDelegate().setValue(value);
    }

    public void setValue(Node value) throws ValueFormatException, VersionException, LockException,
            ConstraintViolationException, RepositoryException
    {
        while (value instanceof NodeWrapper)
        {
            value = ((NodeWrapper)value).getDelegate();
        }
        getDelegate().setValue(value);
    }

}
