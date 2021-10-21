package net.shadowdefender.toolkit.standard.resource.impl;

import net.shadowdefender.toolkit.standard.resource.ResourceRepository;

/**
 * ResourceRepositoryFactory
 *
 * @author shadow
 */
public class ResourceRepositoryFactory {

    public static ResourceRepository getDefaultRepository() {
        return ResourceRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static ResourceRepository defaultRepository = new DefaultResourceRepository();
    }
}