package brix.plugin.template;

import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import brix.BrixNodeModel;
import brix.BrixRequestCycle;
import brix.jcr.JcrUtil;
import brix.jcr.api.JcrNode;
import brix.jcr.api.JcrWorkspace;
import brix.web.picker.node.NodePickerPanel;

public class RestoreItemsPanel extends SelectItemsPanel<Void>
{
    private IModel<JcrNode> targetNode = new BrixNodeModel(null);

    public RestoreItemsPanel(String id, String workspaceName, final String targetWorkspaceName)
    {
        super(id, workspaceName);

        final Component<String> message = new MultiLineLabel<String>("message", new Model<String>(
            ""));
        message.setOutputMarkupId(true);
        add(message);


        add(new NodePickerPanel("picker", targetNode, targetWorkspaceName, null)
        {
            @Override
            public boolean isDisplayFiles()
            {
                return false;
            }
        });

        add(new AjaxLink<Void>("restore")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                List<JcrNode> nodes = getSelectedNodes();
                if (!nodes.isEmpty())
                {
                    JcrWorkspace targetWorkspace = BrixRequestCycle.Locator.getSession(targetWorkspaceName).getWorkspace();
                    Map<JcrNode, List<JcrNode>> dependencies = JcrUtil.getUnsatisfiedDependencies(
                        nodes, targetWorkspace);;
                    if (!dependencies.isEmpty())
                    {                        
                        message.setModelObject(getDependenciesMessage(dependencies));                        
                    }
                    else
                    {
                        JcrNode rootNode = targetNode.getObject();
                        if (rootNode == null)
                        {
                            rootNode = targetWorkspace.getSession().getRootNode();
                        }
                        TemplatePlugin.get().restoreNodes(nodes, rootNode);
                        findParent(ModalWindow.class).close(target);
                    }                    
                }
                else
                {
                    message.setModelObject("You have to select at least one node.");
                }
                target.addComponent(message);
            }
        });
    }


}