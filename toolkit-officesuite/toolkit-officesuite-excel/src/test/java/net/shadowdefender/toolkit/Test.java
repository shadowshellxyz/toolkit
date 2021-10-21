package net.shadowdefender.toolkit;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shadow
 */
public class Test {

    public static void main(String[] args) {

        Map<String, String> fieldCodeAndFieldValueMap = new HashMap<>();
        //活动
        fieldCodeAndFieldValueMap.put("fld1", "五福活动（行业分会场）");
        //PID
        fieldCodeAndFieldValueMap.put("fld2", "2088021999883540");
        //商户名称
        fieldCodeAndFieldValueMap.put("fld3", "深圳市快乐园餐饮管理有限公司");
        //品牌
        fieldCodeAndFieldValueMap.put("fld4", "快乐园");
        //行业
        fieldCodeAndFieldValueMap.put("fld5", "餐饮");
        //BD
        fieldCodeAndFieldValueMap.put("fld6", "风启");
        //业绩团队
        fieldCodeAndFieldValueMap.put("fld7", "南区");
        //流程ID
        fieldCodeAndFieldValueMap.put("fld8", "antprocess-olive_0014_2019122700001000000000043977_2019122700001100000000032269");
        //审核结果
        fieldCodeAndFieldValueMap.put("fld10", "审核中");


        StringBuilder content = new StringBuilder("[");
        for (Map.Entry<String, String> entry : fieldCodeAndFieldValueMap.entrySet()) {
            content.append(String.format("{\\\"code\\\":\\\"%s\\\", \\\"value\\\":\\\"%s\\\"}",
                     entry.getKey(),
                    entry.getValue()));
        }
        content.append("]");

        StringBuilder template = new StringBuilder();


        template.append("{\"command\":\"DynamicRecordUpdateHandler\"")
                .append(", \"content\":").append("\"")
                .append(String.format("update mrchis_kaops_dynamic_record set content = '%s' where uuid = '%s'", content.toString(), "201912271905000000000")).append("\"")
                .append("}");

        System.out.println(template.toString());
    }
}