package org.walkerljl.armor.excel;

/**
 * ScriptBuilderRepositoryFactory
 *
 * @author xingxun
 */
public class ScriptBuilderRepositoryFactory {

    public static ScriptBuilderRepository getDefaultRepository() {
        return Holder.defaultRepository;
    }

    private static class Holder {
        private static ScriptBuilderRepository defaultRepository = new DefaultScriptBuilderRepository();
    }
}