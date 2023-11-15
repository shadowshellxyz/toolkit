
package xyz.shadowshell.toolkit.officesuite;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class Test2 {

    public static void main(String[] args) {

        String coverCityCodeFieldValue = "[\"1\", \"2\"]";
        coverCityCodeFieldValue.replaceAll("\\\\", "");

        List<String> coverCityCodeFieldValueList = JSON.parseArray(coverCityCodeFieldValue, String.class);
        coverCityCodeFieldValue = JSON.toJSONString(coverCityCodeFieldValueList);
        coverCityCodeFieldValue = coverCityCodeFieldValue == null ? "" : coverCityCodeFieldValue.replaceAll("\"", "\\\\\\\\\\\\\"");

        System.out.println(coverCityCodeFieldValue);

    }
}