package net.shadowdefender.toolkit.officesuite;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.shadowdefender.toolkit.lang.MapUtils;
import net.shadowdefender.toolkit.lang.StringUtils;
import net.shadowdefender.toolkit.officesuite.excel.ExcelUtil;

/**
 *
 * @author shadow
 */
public class Test3 {

    public static void main(String[] args) throws Throwable {
        List<List<Object>> dataList = ExcelUtil.read(new FileInputStream("/Users/walkerljl/Downloads/执行结果1 2.xlsx"),

                0, 0, Integer.MAX_VALUE, true);

        System.out.println(dataList.size());
        int counter = 0;
        for (List<Object> row : dataList) {
            if (row == null) {
                continue;
            }

            //System.out.println(JSON.toJSONString(row));
            String industryName = ExcelUtil.getString(row.get(4));
            if (StringUtils.isBlank(industryName)) {
                continue;
            }

            String uuid = ExcelUtil.getString(row.get(1));

            String content = ExcelUtil.getString(row.get(25));
            List<CodeValuePair> cotents = JSON.parseArray(content, CodeValuePair.class);
            List<CodeValuePair> newContents = new ArrayList<>();
            for (CodeValuePair codeValuePair : cotents) {
                if ("fld14".equalsIgnoreCase(codeValuePair.getCode())) {
                    String coverCityCodeFieldValue = codeValuePair.getValue();
                    if (StringUtils.isBlank(coverCityCodeFieldValue)) {
                        continue;
                    }

                    coverCityCodeFieldValue = coverCityCodeFieldValue.replaceAll("\\\\", "");
                    List<String> coverCityCodeFieldValueList = JSON.parseArray(coverCityCodeFieldValue, String.class);
                    coverCityCodeFieldValue = JSON.toJSONString(coverCityCodeFieldValueList);
                    coverCityCodeFieldValue = coverCityCodeFieldValue == null ? "" : coverCityCodeFieldValue.replaceAll("\"", "##########");

                    //coverCityCodeFieldValue = coverCityCodeFieldValue == null ? "" : coverCityCodeFieldValue.replaceAll("\"", "\\\\\\\\\\\\\"");
                    newContents.add(new CodeValuePair(codeValuePair.getCode(), coverCityCodeFieldValue));
                } else {
                    newContents.add(codeValuePair);
                }

                counter ++;
            }
            newContents.add(new CodeValuePair("fld5", industryName));
            content = JSON.toJSONString(newContents);
            content = content.replaceAll("\"", "\\\\\"");
            //content = content.replaceAll("##########", "\\\\\\\\\\\\\\\\\\\\\\\\\\\"");

            String contentMap = ExcelUtil.getString(row.get(26));
            Map<String, String> contentMapMap = JSON.parseObject(contentMap, Map.class);
            String coverCityCodeFieldValue = MapUtils.get(contentMapMap, "小程序覆盖城市编码");
            if (StringUtils.isNotBlank(coverCityCodeFieldValue)) {
                coverCityCodeFieldValue = coverCityCodeFieldValue.replaceAll("\\\\", "");
                List<String> coverCityCodeFieldValueList = JSON.parseArray(coverCityCodeFieldValue, String.class);
                coverCityCodeFieldValue = JSON.toJSONString(coverCityCodeFieldValueList);
                coverCityCodeFieldValue = coverCityCodeFieldValue == null ? "" : coverCityCodeFieldValue.replaceAll("\"", "##########");
                contentMapMap.put("小程序覆盖城市编码", coverCityCodeFieldValue);
            }

            contentMapMap.put("行业", industryName);
            contentMap = JSON.toJSONString(contentMapMap);
            contentMap = contentMap.replaceAll("\"", "\\\\\"");

            String sql = String.format("update mrchis_kaops_dynamic_record set search_field5 = '%s', content = '%s', content_map = '%s' where uuid = '%s'",
                    industryName,
                    content,
                    contentMap,
                    uuid);

            System.out.println(sql);

            //StringBuilder template = new StringBuilder();
            //template.append("{\"command\":\"DynamicRecordUpdateHandler\"")
            //        .append(", \"content\":").append("\"")
            //        .append(sql).append("\"")
            //        .append("}");
            //
            //System.out.println(template.toString());
        }

        System.out.println(counter);
    }
}

class CodeValuePair implements Serializable {

    private static final long serialVersionUID = -783552372712236028L;

    private String code;
    private String value;

    public CodeValuePair() {}

    public CodeValuePair(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code  value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value  value to be assigned to property value
     */
    public void setValue(String value) {
        this.value = value;
    }
}