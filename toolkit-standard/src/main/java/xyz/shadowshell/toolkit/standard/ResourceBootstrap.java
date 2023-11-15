package xyz.shadowshell.toolkit.standard;

import xyz.shadowshell.toolkit.standard.resource.Resource;
import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;
import xyz.shadowshell.toolkit.standard.resource.abstracts.AbstractResource;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;
import xyz.shadowshell.toolkit.standard.resource.impl.ResourceRepositoryFactory;

/**
 * ResourceBootstrap
 *
 * @author shadow
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