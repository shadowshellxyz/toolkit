package net.shadowdefender.toolkit.lang;

/**
 * HtmlUtils
 * @author shadow
 */
public final class HtmlUtils {

    private HtmlUtils() {}

    public static String escapeJson(String json) {
        return json.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
