package com.example.zerobase_study22;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void testHistoryServiceSQLite() {
        historyServiceSQLite.Insert(new history(Double.toString(37.5455744), Double.toString(126.877696)));
        historyServiceSQLite.Insert(new history(Double.toString(37.5455744), Double.toString(126.877696)));

        List<history> his = historyServiceSQLite.list();
        for (history hi : his) {
            System.out.println(hi);
        }

        history his1 = new history();
        his1.setId(2);
        historyServiceSQLite.withdraw(his1);
    }

    public static void testWifiinfoServiceSQLite() {
        WifinfoServiceSQLite.clearTable();

        List<Wifiinfo> results = null;
        try {
            results = ApiExplorer.collectWifiInfos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WifinfoServiceSQLite.insertAll(results);

        List<Wifiinfo> results2 = WifinfoServiceSQLite.listNear(Double.toString(37.5455744), Double.toString(126.877696));
        for (Wifiinfo wifiinfo : results2) {
            System.out.println(wifiinfo);
        }
    }
    public static void main(String[] args) {
        testHistoryServiceSQLite();
    }
}
