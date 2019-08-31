package org.walkerljl.toolkit.logging.extension;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

/**
 * @author lijunlin
 */
public class Log4jExPatternLayout extends PatternLayout {

    public Log4jExPatternLayout(String pattern) {
        super(pattern);
    }

    public Log4jExPatternLayout() {
        super();
    }

    /**
     * 重写createPatternParser方法，返回PatternParser的子类
     */
    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new org.walkerljl.toolkit.logging.extension.Log4jExPatternParser(pattern);
    }

}
