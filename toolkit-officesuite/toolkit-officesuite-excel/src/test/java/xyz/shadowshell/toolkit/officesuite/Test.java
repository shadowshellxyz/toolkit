//
//package xyz.shadowshell.toolkit.officesuite;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * @author shadow
// */
//public class Test {
//
//    @org.junit.Test
//    public void test() throws IOException {
//        File f = new File("/Users/walkerljl/workbench/note/draft.json");
//        if (f.isFile() && f.exists()) {
//            InputStream file = new FileInputStream(f);
//            InputStreamReader read = new InputStreamReader(file, "UTF-8");
//            BufferedReader bufferedReader = new BufferedReader(read);
//            StringBuilder content = new StringBuilder();
//
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//
//                System.out.print(line);
//                content.append(line);
//            }
//
//            JSONArray jsonArray = JSON.parseArray(content.toString());
//            Iterator iterator = jsonArray.iterator();
//
//            Set<String> exists = new HashSet<>();
//            do {
//                JSONObject jsonObject = (JSONObject) iterator.next();
//                String pid = jsonObject.getString("pid");
//                String activityId = jsonObject.getString("activityId");
//                String id = String.format("%s.%s", pid, activityId);
//
//                if (exists.contains(id)) {
//                    System.out.println("Exists:" + id);
//                } else {
//                    exists.add(id);
//                }
//
//            } while (iterator.hasNext());
//        } else {
//            System.out.print("this file is not exit");
//        }
//    }
//}