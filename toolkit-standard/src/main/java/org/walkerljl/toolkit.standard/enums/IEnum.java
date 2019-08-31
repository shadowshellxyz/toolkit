package org.walkerljl.toolkit.standard.enums;

/**
 * Enum common interface
 *
 * @author xingxun
 */
public interface IEnum {

    /**
     * Get code
     *
     * @return
     */
    String getCode();

    /**
     * Get description
     *
     * @return
     */
    String getDescription();

    /**
     * Get enum object
     *
     * @param code Code
     * @return
     */
    IEnum getEnumObject(String code);
}