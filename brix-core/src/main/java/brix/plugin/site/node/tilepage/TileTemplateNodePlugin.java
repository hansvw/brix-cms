package brix.plugin.site.node.tilepage;

import org.apache.wicket.model.IModel;

import brix.Brix;
import brix.jcr.api.JcrNode;
import brix.jcr.wrapper.BrixNode;
import brix.plugin.site.NodeConverter;
import brix.plugin.site.node.tilepage.admin.CreateTilePagePanel;
import brix.web.admin.navigation.NavigationAwarePanel;

public class TileTemplateNodePlugin extends TileNodePlugin
{

    public static final String TYPE = Brix.NS_PREFIX + "tileTemplate";

    public TileTemplateNodePlugin()
    {

    }

    @Override
    public NodeConverter getConverterForNode(JcrNode node)
    {
        if (TilePageNodePlugin.TYPE.equals(((BrixNode)node).getNodeType()))
            return new FromPageConverter(getNodeType());
        else
            return super.getConverterForNode(node);
    }

    private static class FromPageConverter extends SetTypeConverter
    {
        public FromPageConverter(String type)
        {
            super(type);
        }
    };

    @Override
    public NavigationAwarePanel newCreateNodePanel(String id, IModel<JcrNode> parentNode)
    {
        return new CreateTilePagePanel(id, parentNode, getNodeType());
    }

    @Override
    public String getNodeType()
    {
        return TYPE;
    }

    public String getName()
    {
        return "Tile Template";
    }

}
