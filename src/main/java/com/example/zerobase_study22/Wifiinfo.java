package com.example.zerobase_study22;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wifiinfo {
    @SerializedName("X_SWIFI_MGR_NO")
    private String mgrNo;  // 관리번호

    private Double dist;

    @SerializedName("X_SWIFI_WRDOFC")
    private String wrdofc;  // 자치구

    @SerializedName("X_SWIFI_MAIN_NM")
    private String mainNm; // 와이파이명

    @SerializedName("X_SWIFI_ADRES1")
    private String adres1;  // 도로명주소

    @SerializedName("X_SWIFI_ADRES2")
    private String adres2;  // 상세 주소

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String instlFloor; // 설치위치(층)

    @SerializedName("X_SWIFI_INSTL_TY")
    private String instlTy;    // 설치유형

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String instlMby;   // 설치기관

    @SerializedName("X_SWIFI_SVC_SE")
    private String svcSe;  // 서비스 구분

    @SerializedName("X_SWIFI_CMCWR")
    private String cmcwr;   // 망종류

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String cnstcYear;   // 설치년도

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inoutDoor;   // 실내외구분

    @SerializedName("X_SWIFI_REMARS3")
    private String remars3; // wifi 접속환경

    @SerializedName("LAT")
    private String lat; // y좌표

    @SerializedName("LNT")
    private String lnt; // x좌표

    @SerializedName("WORK_DTTM")
    private String workDttm;    // 작업일자

    public String getRoundDist() {
        return String.format("%.2f", dist);
    }

}
