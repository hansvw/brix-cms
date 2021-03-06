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

package brix.plugin.site.auth;

import brix.auth.AbstractNodeAction;
import brix.jcr.wrapper.BrixNode;

public class ConvertNodeAction extends AbstractNodeAction
{
    private final String targetType;

    public ConvertNodeAction(Context context, BrixNode node, String targetType)
    {
        super(context, node);
        this.targetType = targetType;
    }

    public String getTargetNodeType()
    {
        return targetType;
    }

    @Override
    public String toString()
    {
        return "ConvertNodeAction{" + "targetType='" + targetType + '\'' + "} " + super.toString();
    }
}
