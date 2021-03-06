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

/**
 * 
 */
package brix.plugin.menu.tile;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import brix.jcr.wrapper.BrixNode;
import brix.plugin.menu.MenuPlugin;

/**
 * A model that creates a list of all available menu nodes
 * 
 * @author igor.vaynberg
 * 
 */
public final class MenuNodesListModel extends LoadableDetachableModel<List<BrixNode>>
{
	private final IModel<BrixNode> workspaceNodeModel;

	MenuNodesListModel(IModel<BrixNode> workspaceNodeModel)
	{
		this.workspaceNodeModel = workspaceNodeModel;
	}

	@Override
	protected List<BrixNode> load()
	{
		return MenuPlugin.get().getMenuNodes(
				workspaceNodeModel.getObject().getSession().getWorkspace().getName());
	}

	@Override
	public void detach()
	{
		super.detach();
		workspaceNodeModel.detach();
	}
}