package org.walkerljl.toolkit.standard.resource.impl;

import org.walkerljl.toolkit.standard.resource.ResourceRepository;

/**
 * ResourceRepositoryFactory
 *
 * @author xingxun
 */
public class ResourceRepositoryFactory {

    public static ResourceRepository getDefaultRepository() {
        return ResourceRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static ResourceRepository defaultRepository = new DefaultResourceRepository();
    }
}