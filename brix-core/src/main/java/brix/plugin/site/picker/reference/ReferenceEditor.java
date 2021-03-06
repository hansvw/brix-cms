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

package brix.plugin.site.picker.reference;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import brix.web.generic.BrixGenericPanel;
import brix.web.nodepage.BrixPageParameters;
import brix.web.reference.Reference;
import brix.web.tab.BrixAjaxTabbedPanel;
import brix.web.tab.CachingAbstractTab;
import brix.web.tab.IBrixTab;

public class ReferenceEditor extends BrixGenericPanel<Reference>
{

    public ReferenceEditor(String id, ReferenceEditorConfiguration configuration)
    {
        super(id);
        this.configuration = configuration;
        init();
    }

    public ReferenceEditor(String id, IModel<Reference> model, ReferenceEditorConfiguration configuration)
    {
        super(id, model);
        this.configuration = configuration;
        init();
    }

    private Reference getReference()
    {
        return (Reference)getModelObject();
    }

    private void init()
    {
        List<IBrixTab> tabs = new ArrayList<IBrixTab>();
        tabs.add(new CachingAbstractTab(new ResourceModel("reference"))
        {
            @Override
            public Panel newPanel(String panelId)
            {
                return new NodeUrlTab(panelId, getModel())
                {
                    @Override
                    protected ReferenceEditorConfiguration getConfiguration()
                    {
                        return configuration;
                    }
                };
            }

            @Override
            public boolean isVisible()
            {
                return configuration.isAllowNodePicker() || configuration.isAllowURLEdit();
            }
        });
        tabs.add(new CachingAbstractTab(new ResourceModel("queryParameters"))
        {
            @Override
            public Panel newPanel(String panelId)
            {
                return new QueryParametersTab(panelId)
                {
                    @Override
                    protected BrixPageParameters getPageParameters()
                    {
                        return getReference().getParameters();
                    };
                };
            }

            @Override
            public boolean isVisible()
            {
                return configuration.isAllowQueryParameters();
            }
        });
        tabs.add(new CachingAbstractTab(new ResourceModel("indexedParameters"))
        {
            @Override
            public Panel newPanel(String panelId)
            {
                return new IndexedParametersTab(panelId)
                {
                    @Override
                    protected BrixPageParameters getPageParameters()
                    {
                        return getReference().getParameters();
                    }
                };
            }

            @Override
            public boolean isVisible()
            {
                return configuration.isAllowIndexedParameters();
            }
        });

        add(new BrixAjaxTabbedPanel("tabbedPanel", tabs));
    }

    private final ReferenceEditorConfiguration configuration;
}
