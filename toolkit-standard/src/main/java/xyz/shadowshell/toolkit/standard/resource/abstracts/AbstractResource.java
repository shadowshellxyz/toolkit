package xyz.shadowshell.toolkit.standard.resource.abstracts;

import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.logging.Logger;
import xyz.shadowshell.toolkit.logging.LoggerFactory;
import xyz.shadowshell.toolkit.standard.resource.Resource;
import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;
import xyz.shadowshell.toolkit.standard.resource.impl.ResourceRepositoryFactory;

/**
 * 抽象的资源
 *
 * @author shadow
 * @Date 2016/12/9
 */
public abstract class AbstractResource implements Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractResource.class);

    /**
     * 是否初始化标志
     */
    private volatile boolean inited = false;

    private ResourceRepository resourceRepository = ResourceRepositoryFactory.getDefaultRepository();

    /***
     * 处理初始化
     *
     * @throws CannotInitResourceException
     */
    public abstract void processInit() throws CannotInitResourceException;

    /**
     * 处理销毁
     *
     * @throws CannotDestroyResourceException
     */
    public abstract void processDestroy() throws CannotDestroyResourceException;

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
        Class<?>[] interfaces = getClass().getInterfaces();
        return interfaces == null || interfaces.length < 1 ? null : interfaces[0].getSimpleName();
    }

    @Override
    public String getInstanceId() {
        return this.toString();
    }

    @Override
    public Resource init() throws CannotInitResourceException {
        long startTime = System.currentTimeMillis();
        try {
            if (!inited) {
                synchronized (this) {
                    if (!inited) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is initing.", getServerName()));
                        }

                        processInit();
                        resourceRepository.register(resourceRepository.buildKey(getGroup(), getId()), this);
                        inited = true;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has inited,consume %s milliseconds.", getServerName(),
                                    (System.currentTimeMillis() - startTime)));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when initing.", getServerName()), e);
            throw new CannotInitResourceException(e);
        }
        return this;
    }

    @Override
    public Resource destroy() throws CannotDestroyResourceException {
        long startTime = System.currentTimeMillis();
        try {
            if (inited) {
                synchronized (this) {
                    if (inited) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is destroying.", getServerName()));
                        }

                        processDestroy();
                        resourceRepository.unregister(resourceRepository.buildKey(getGroup(), getId()));
                        inited = false;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has destroied,consume %s milliseconds.", getServerName(),
                                    (System.currentTimeMillis() - startTime)));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when destroying.", getServerName()), e);
            throw new CannotDestroyResourceException(e);
        }
        return this;
    }

    /**
     * Get service name
     *
     * @return
     */
    protected String getServerName() {
        return String.format("Resource[id=%s,group=%s,instanceId=%s]", getId(), getGroup(), getInstanceId());
    }
}
