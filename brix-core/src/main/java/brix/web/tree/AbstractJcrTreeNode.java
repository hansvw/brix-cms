/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package brix.web.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Objects;

import brix.BrixNodeModel;
import brix.auth.Action.Context;
import brix.jcr.api.JcrNodeIterator;
import brix.jcr.wrapper.BrixNode;
import brix.plugin.site.SitePlugin;


public class AbstractJcrTreeNode implements JcrTreeNode, IDetachable
{
    private final IModel<BrixNode> nodeModel;

    public AbstractJcrTreeNode(IModel<BrixNode> nodeModel)
    {
        if (nodeModel == null)
        {
            throw new IllegalArgumentException("Argument 'nodeModel' may not be null.");
        }
        this.nodeModel = nodeModel;
    }

    public AbstractJcrTreeNode(BrixNode node)
    {
        if (node == null)
        {
            throw new IllegalArgumentException("Argument 'node' may not be null.");
        }
        this.nodeModel = new BrixNodeModel(node);
    }

    public IModel<BrixNode> getNodeModel()
    {
        return nodeModel;
    }


    @Override
    public int hashCode()
    {
        return nodeModel.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj instanceof AbstractJcrTreeNode == false)
            return false;
        AbstractJcrTreeNode that = (AbstractJcrTreeNode)obj;

        return Objects.equal(nodeModel, that.nodeModel);
    }

    private transient List<AbstractJcrTreeNode> children;

    private void sortChildren(List<AbstractJcrTreeNode> children)
    {
        Collections.sort(children, new Comparator<AbstractJcrTreeNode>()
        {
            public int compare(AbstractJcrTreeNode o1, AbstractJcrTreeNode o2)
            {
                BrixNode n1 = (BrixNode)o1.nodeModel.getObject();
                BrixNode n2 = (BrixNode)o2.nodeModel.getObject();

                if (n1.isFolder() && !n2.isFolder())
                {
                    return -1;
                }
                else if (n2.isFolder() && !n1.isFolder())
                {
                    return 1;
                }
                return n1.getName().compareToIgnoreCase(n2.getName());
            }
        });
    }

    protected boolean displayFoldersOnly()
    {
        return false;
    }

    protected AbstractJcrTreeNode newTreeNode(BrixNode node)
    {
        return new AbstractJcrTreeNode(node);
    }

    private List<AbstractJcrTreeNode> loadChildren()
    {
        List<AbstractJcrTreeNode> children = new ArrayList<AbstractJcrTreeNode>();
        JcrNodeIterator iterator = nodeModel.getObject().getNodes();
        List<BrixNode> entries = new ArrayList<BrixNode>((int)iterator.getSize());
        while (iterator.hasNext())
        {
            entries.add((BrixNode)iterator.nextNode());
        }

        for (BrixNode entry : entries)
        {        	            
            if (!entry.isHidden() && (displayFoldersOnly() == false || entry.isFolder()) &&
                SitePlugin.get().canViewNodeChildren(entry, Context.ADMINISTRATION))
            {
                children.add(newTreeNode(entry));
            }
        }

        sortChildren(children);

        return children;
    }


    public List<AbstractJcrTreeNode> getChildren()
    {
        if (children == null)
        {
            if (SitePlugin.get().canViewNodeChildren(nodeModel.getObject(), Context.ADMINISTRATION))
            {
                children = loadChildren();
            }
            else
            {
                children = Collections.emptyList();
            }
        }
        return children;
    }

    public Enumeration< ? > children()
    {
        return Collections.enumeration(getChildren());
    }

    public boolean isLeaf()
    {
        return ((BrixNode)nodeModel.getObject()).isFolder() == false;
    }

    public void detach()
    {
        children = null;
        nodeModel.detach();
    }

    @Override
    public String toString()
    {
    	BrixNode node = nodeModel.getObject();
        return node != null ? node.toString() : "null";
    }

}
