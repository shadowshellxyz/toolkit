package xyz.shadowshell.toolkit.standard.resource.impl;

import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;

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