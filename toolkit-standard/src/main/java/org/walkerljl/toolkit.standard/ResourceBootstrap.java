package org.walkerljl.toolkit.standard;

import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.standard.resource.impl.ResourceRepositoryFactory;

/**
 * ResourceBootstrap
 *
 * @author xingxun
 */
public class ResourceBootstrap extends AbstractResource implements Resource {

    private ResourceRepository resourceRepository = ResourceRepositoryFactory.getDefaultRepository();

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getGroup() {
        return getClass().getInterfaces()[0].getSimpleName();
    }

    @Override
    public void processInit() throws CannotInitResourceException {
        for (Resource resource : resourceRepository.lookupAll()) {
            if (resource == null) {
                continue;
            }
            if (getGroup().equalsIgnoreCase(resource.getGroup())
                    && getId().equalsIgnoreCase(resource.getId())) {
                continue;
            }

            resource.init();
        }
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        for (Resource resource : resourceRepository.lookupAll()) {
            if (resource == null) {
                continue;
            }
            if (getGroup().equalsIgnoreCase(resource.getGroup())
                    && getId().equalsIgnoreCase(resource.getId())) {
                continue;
            }

            resource.destroy();
        }
    }
}