//package xyz.shadowshell.toolkit.officesuite;
//
//import java.util.Iterator;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * @author shadow
// */
//public class FileExtractorTest {
//
//    public static void main(String[] args) {
//
//        try {
//            JSONObject jsonObject = JSON.parseObject(FileExtractor.extract(
//                    "/Users/walkerljl/workbench/desktop/优惠券扫码弹屏.json"
//            ));
//
//            JSONArray jsonArray = jsonObject.getJSONArray("prizeSummarys");
//            int counter = 0;
//            Iterator<?> iterator = jsonArray.iterator();
//            while (iterator.hasNext()) {
//                JSONObject summary = (JSONObject) iterator.next();
//                String appType = summary.getString("appType");
//                String budgetType = summary.getString("budgetType");
//                //if (!"STANDARD".equalsIgnoreCase(appType)) {
//                //    continue;
//                //}
//
//                if (!"AMOUNT".equalsIgnoreCase(budgetType)) {
//                    continue;
//                }
//
//                counter++;
//            }
//
//            System.out.println(counter);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//    }
//}