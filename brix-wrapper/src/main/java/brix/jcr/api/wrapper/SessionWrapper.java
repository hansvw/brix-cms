package brix.jcr.api.wrapper;

import java.io.InputStream;
import java.io.OutputStream;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.xml.sax.ContentHandler;

import brix.jcr.api.JcrItem;
import brix.jcr.api.JcrNode;
import brix.jcr.api.JcrSession;
import brix.jcr.api.JcrValueFactory;
import brix.jcr.api.JcrWorkspace;

/**
 * 
 * @author Matej Knopp
 */
class SessionWrapper extends AbstractWrapper implements JcrSession
{

    protected SessionWrapper(Session delegate, Behavior behavior)
    {
        super(delegate, null);
        this.behavior = behavior;
    }

    private final Behavior behavior;

    public Behavior getBehavior()
    {
        return behavior;
    }

    @Override
    protected JcrSession getJcrSession()
    {
        return this;
    }

    public static JcrSession wrap(Session delegate, Behavior behavior)
    {
        if (delegate == null)
        {
            return null;
        }
        else
        {
            return new SessionWrapper(delegate, behavior);
        }
    }

    @Override
    public Session getDelegate()
    {
        return (Session)super.getDelegate();
    }

    public void addLockToken(final String lt)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().addLockToken(lt);
            }
        });
    }

    public void checkPermission(final String absPath, final String actions)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().checkPermission(absPath, actions);
            }
        });
    }

    public void exportDocumentView(final String absPath, final ContentHandler contentHandler,
            final boolean skipBinary, final boolean noRecurse)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().exportDocumentView(absPath, contentHandler, skipBinary, noRecurse);
            }
        });
    }

    public void exportDocumentView(final String absPath, final OutputStream out,
            final boolean skipBinary, final boolean noRecurse)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().exportDocumentView(absPath, out, skipBinary, noRecurse);
            }
        });
    }

    public void exportSystemView(final String absPath, final ContentHandler contentHandler,
            final boolean skipBinary, final boolean noRecurse)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().exportSystemView(absPath, contentHandler, skipBinary, noRecurse);
            }
        });
    }

    public void exportSystemView(final String absPath, final OutputStream out,
            final boolean skipBinary, final boolean noRecurse)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().exportSystemView(absPath, out, skipBinary, noRecurse);
            }
        });
    }

    public Object getAttribute(final String name)
    {
        return executeCallback(new Callback<Object>()
        {
            public Object execute() throws Exception
            {
                return getDelegate().getAttribute(name);
            }
        });
    }

    public String[] getAttributeNames()
    {
        return executeCallback(new Callback<String[]>()
        {
            public String[] execute() throws Exception
            {
                return getDelegate().getAttributeNames();
            }
        });
    }

    public ContentHandler getImportContentHandler(final String parentAbsPath, final int uuidBehavior)
    {
        return executeCallback(new Callback<ContentHandler>()
        {
            public ContentHandler execute() throws Exception
            {
                return getDelegate().getImportContentHandler(parentAbsPath, uuidBehavior);
            }
        });
    }

    public JcrItem getItem(final String absPath)
    {
        return executeCallback(new Callback<JcrItem>()
        {
            public JcrItem execute() throws Exception
            {
                return JcrItem.Wrapper.wrap(getDelegate().getItem(absPath), getJcrSession());
            }
        });
    }

    public String[] getLockTokens()
    {
        return executeCallback(new Callback<String[]>()
        {
            public String[] execute() throws Exception
            {
                return getDelegate().getLockTokens();
            }
        });
    }

    public String getNamespacePrefix(final String uri)
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getNamespacePrefix(uri);
            }
        });
    }

    public String[] getNamespacePrefixes()
    {
        return executeCallback(new Callback<String[]>()
        {
            public String[] execute() throws Exception
            {
                return getDelegate().getNamespacePrefixes();
            }
        });
    }

    public String getNamespaceURI(final String prefix)
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getNamespaceURI(prefix);
            }
        });
    }

    public JcrNode getNodeByUUID(final String uuid)
    {
        return executeCallback(new Callback<JcrNode>()
        {
            public JcrNode execute() throws Exception
            {
                return JcrNode.Wrapper.wrap(getDelegate().getNodeByUUID(uuid), getJcrSession());
            }
        });
    }

    public Repository getRepository()
    {
        return executeCallback(new Callback<Repository>()
        {
            public Repository execute() throws Exception
            {
                return getDelegate().getRepository();
            }
        });
    }

    public JcrNode getRootNode()
    {
        return executeCallback(new Callback<JcrNode>()
        {
            public JcrNode execute() throws Exception
            {
                return JcrNode.Wrapper.wrap(getDelegate().getRootNode(), getJcrSession());
            }
        });
    }

    public String getUserID()
    {
        return executeCallback(new Callback<String>()
        {
            public String execute() throws Exception
            {
                return getDelegate().getUserID();
            }
        });
    }

    public JcrValueFactory getValueFactory()
    {
        return executeCallback(new Callback<JcrValueFactory>()
        {
            public JcrValueFactory execute() throws Exception
            {
                return JcrValueFactory.Wrapper.wrap(getDelegate().getValueFactory(),
                        getJcrSession());
            }
        });
    }

    public JcrWorkspace getWorkspace()
    {
        return executeCallback(new Callback<JcrWorkspace>()
        {
            public JcrWorkspace execute() throws Exception
            {
                return JcrWorkspace.Wrapper.wrap(getDelegate().getWorkspace(), getJcrSession());
            }
        });
    }

    public boolean hasPendingChanges()
    {
        return executeCallback(new Callback<Boolean>()
        {
            public Boolean execute() throws Exception
            {
                return getDelegate().hasPendingChanges();
            }
        });
    }

    public JcrSession impersonate(final Credentials credentials)
    {
        return executeCallback(new Callback<JcrSession>()
        {
            public JcrSession execute() throws Exception
            {
                return JcrSession.Wrapper.wrap(getDelegate().impersonate(credentials),
                        getBehavior());
            }
        });
    }

    public void importXML(final String parentAbsPath, final InputStream in, final int uuidBehavior)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().importXML(parentAbsPath, in, uuidBehavior);
            }
        });
    }

    public boolean isLive()
    {
        return executeCallback(new Callback<Boolean>()
        {
            public Boolean execute() throws Exception
            {
                return getDelegate().isLive();
            }
        });
    }

    public boolean itemExists(final String absPath)
    {
        return executeCallback(new Callback<Boolean>()
        {
            public Boolean execute() throws Exception
            {
                return getDelegate().itemExists(absPath);
            }
        });
    }

    public void logout()
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().logout();
            }
        });
    }

    public void move(final String srcAbsPath, final String destAbsPath)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().move(srcAbsPath, destAbsPath);
            }
        });
    }

    public void refresh(final boolean keepChanges)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().refresh(keepChanges);
            }
        });
    }

    public void removeLockToken(final String lt)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().removeLockToken(lt);
            }
        });
    }

    public void save()
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().save();
            }
        });
    }

    public void setNamespacePrefix(final String prefix, final String uri)
    {
        executeCallback(new VoidCallback()
        {
            public void execute() throws Exception
            {
                getDelegate().setNamespacePrefix(prefix, uri);
            }
        });
    }
}
