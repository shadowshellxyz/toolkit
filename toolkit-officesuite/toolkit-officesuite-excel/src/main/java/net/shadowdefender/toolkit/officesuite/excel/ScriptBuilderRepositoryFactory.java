package net.shadowdefender.toolkit.officesuite.excel;

/**
 * ScriptBuilderRepositoryFactory
 *
 * @author shadow
 */
public class ScriptBuilderRepositoryFactory {

    public static ScriptBuilderRepository getDefaultRepository() {
        return Holder.defaultRepository;
    }

    private static class Holder {
        private static ScriptBuilderRepository defaultRepository = new DefaultScriptBuilderRepository();
    }
}