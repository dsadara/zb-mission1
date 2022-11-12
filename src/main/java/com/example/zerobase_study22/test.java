package com.example.zerobase_study22;

public class test {
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
//        List<Wifiinfo> results = WifiinfoServiceMariaDB.list();

//        WifiinfoServiceMariaDB.deleteWrongLat();
    }
}
