package xyz.shadowshell.toolkit.standard.model.param;

import xyz.shadowshell.toolkit.standard.model.BaseModel;

/**
 * BaseParam
 *
 * @author shadow
 */
public class BaseParam extends BaseModel {

    private static final long serialVersionUID = -2259152822289042661L;

    /**
     * 渠道
     */
    private String channel;
    /**
     * 操作者ID
     */
    private String operatorId;

    /**
     * Getter method for property <tt>channel</tt>.
     *
     * @return property value of channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Setter method for property <tt>channel</tt>.
     *
     * @param channel value to be assigned to property channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Getter method for property <tt>operatorId</tt>.
     *
     * @return property value of operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Setter method for property <tt>operatorId</tt>.
     *
     * @param operatorId value to be assigned to property operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}