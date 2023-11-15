package xyz.shadowshell.toolkit.standard.enums;

import java.util.Collection;

/**
 * Enum common interface
 *
 * @author shadow
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
    default IEnum getEnumObject(String code) {
        for (IEnum ele : listValues()) {
            if (ele.getCode().equalsIgnoreCase(code)) {
                return ele;
            }
        }
        return null;
    }

    default Collection<IEnum> listValues() {
        return null;
    }
}