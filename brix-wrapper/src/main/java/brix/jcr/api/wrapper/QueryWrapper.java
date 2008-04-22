package brix.jcr.api.wrapper;

import javax.jcr.query.Query;

import brix.jcr.api.JcrNode;
import brix.jcr.api.JcrQuery;
import brix.jcr.api.JcrQueryResult;
import brix.jcr.api.JcrSession;

/**
 * 
 * @author Matej Knopp
 */
class QueryWrapper extends AbstractWrapper implements JcrQuery
{

    protected QueryWrapper(Query delegate, JcrSession session)
    {
        super(delegate, session);
    }

    public static JcrQuery wrap(Query delegate, JcrSession session)
    {
        if (delegate == null)
        {
            return null;
        }
        else
        {
            return new QueryWrapper(delegate, session);
        }
    }

    @Override
    public Query getDelegate()
    {
        return (Query)super.getDelegate();
    }

    public JcrQueryResult execute()
    {
        return executeCallback(new Callback<JcrQueryResult>()
        {
            public JcrQueryResult execute() throws Exception
            {
                return JcrQueryResult.Wrapper.wrap(getDelegate().execute(), getJcrSession());
            }
        });
    }

    public String getLanguage()
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getLanguage();
            }
        });
    }

    public String getStatement()
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getStatement();
            }
        });
    }

    public String getStoredQueryPath()
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getStoredQueryPath();
            }
        });
    }

    public JcrNode storeAsNode(final String absPath)
    {
        return executeCallback(new Callback<JcrNode>()
        {
            public JcrNode execute() throws Exception
            {
                return JcrNode.Wrapper.wrap(getDelegate().storeAsNode(absPath), getJcrSession());
            }
        });
    }

}
