package com.example.zerobase_study22;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.net.URLEncoder.*;

public class ApiExplorer {
    public static String apiCall(int startIndex, int endIndex) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");    /*URL*/
        urlBuilder.append("/" + encode("454c6d67686c696d3832466a5a476b", "UTF-8"));     /*인증키*/
        urlBuilder.append("/" + encode("json", "UTF-8"));   /*요청파일타입*/
        urlBuilder.append("/" + encode("TbPublicWifiInfo", "UTF-8"));   /*서비스명*/
        urlBuilder.append("/" + encode(String.valueOf(startIndex), "UTF-8"));   /*요청시작위치*/
        urlBuilder.append("/" + encode(String.valueOf(endIndex), "UTF-8"));   /*요청종료위치*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
//        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;
// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void putJsonStringToClass(String res, ArrayList<Wifiinfo> results) {
        JsonObject tbPublicWifiInfo = JsonParser.parseString(res).getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
        JsonArray rows = tbPublicWifiInfo.get("row").getAsJsonArray();

        for (int i = 0; i < rows.size(); i++) {
            JsonElement row = rows.get(i);
            Gson gson = new Gson();
            Wifiinfo wifiInfo = gson.fromJson(row, Wifiinfo.class);
            results.add(wifiInfo);
        }

    }
    public static ArrayList<Wifiinfo> collectWifiInfos() throws IOException {
        ArrayList<Wifiinfo> results = new ArrayList<>();

        // 와이파이 개수 가져오기
        String jsonStr = apiCall(1, 1);
        JsonObject tbPublicWifiInfo = JsonParser.parseString(jsonStr).getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
        int wifiNum = tbPublicWifiInfo.get("list_total_count").getAsInt();
        System.out.println(wifiNum);

        int iterate1000Num = wifiNum / 1000;
        int lastIterateNum = wifiNum % 1000;

        // 1000개씩 데이터 가져오기
        int startIndex;
        int endIndex;
        for (int i = 0; i < iterate1000Num; i++) {
            startIndex = (i) * 1000 + 1;
            endIndex = (i) * 1000 + 1000;
            jsonStr = apiCall(startIndex, endIndex);
            putJsonStringToClass(jsonStr, results);
        }

        // 남은 데이터 가져오기
        startIndex = iterate1000Num * 1000 + 1;
        endIndex = startIndex + lastIterateNum - 1;
        jsonStr = apiCall(startIndex, endIndex);
        putJsonStringToClass(jsonStr, results);

        return results;
    }

    public static void main(String[] args) {
//        String result;
//        try {
//            result = apiCall(5);
//            System.out.println(result);
//
//            JsonElement element = JsonParser.parseString(result);
//            JsonObject object = element.getAsJsonObject();
//
//            JsonObject tbPublicWifiInfo = object.get("TbPublicWifiInfo").getAsJsonObject();
//            int list_total_count = tbPublicWifiInfo.get("list_total_count").getAsInt();
//            System.out.println("데이터 개수: " + list_total_count);
//
//            JsonObject res = tbPublicWifiInfo.get("RESULT").getAsJsonObject();
//
//            JsonArray rows = tbPublicWifiInfo.get("row").getAsJsonArray();
//            for (int i = 0; i < rows.size(); i++) {
//                JsonElement row = rows.get(i);
//                Gson gson = new Gson();
//                WifiInfo wifiInfo = gson.fromJson(row, WifiInfo.class);
//                System.out.println(wifiInfo);
//            }
//            ArrayList<Wifiinfo> results = collectWifiInfos();
//            WifiinfoServiceMariaDB.insertAll(results);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        List<Wifiinfo> results = WifiinfoServiceMariaDB.list();
    }

}
