/*
 * Copyright 2011 Vincent Behar
 *
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
package org.sonatype.nexus.plugins.rundeck;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import org.restlet.data.MediaType;
import org.restlet.resource.Variant;
import org.sonatype.nexus.plugins.rundeck.model.Option;
import org.sonatype.plexus.rest.resource.AbstractPlexusResource;
import org.sonatype.plexus.rest.resource.PathProtectionDescriptor;

/**
 * Option provider for RunDeck - see http://rundeck.org/docs/RunDeck-Guide.html#option-model-provider<br>
 * Abstract class for writing option providers.
 * 
 * @author Vincent Behar
 */
public abstract class AbstractOptionProvider extends AbstractPlexusResource {

    @Override
    public List<Variant> getVariants() {
        return Arrays.asList(new Variant(MediaType.APPLICATION_JSON));
    }

    @Override
    public PathProtectionDescriptor getResourceProtection() {
        // should be new PathProtectionDescriptor(this.getResourceUri(), "anon");
        // BUT https://issues.sonatype.org/browse/NEXUS-3951
        return new PathProtectionDescriptor(getResourceUri(), "authcBasic");
    }

    @Override
    public Object getPayloadInstance() {
        return null;
    }

    @Override
    public void configureXStream(XStream xstream) {
        super.configureXStream(xstream);

        xstream.processAnnotations(Option.class);
    }

}
