package com.example.zerobase_study22;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class history {
    @SerializedName("ID")
    private int id;
    @SerializedName("LAT")
    private String lat;
    @SerializedName("LNT")
    private String lnt;
    @SerializedName("DATE")
    private String date;

    public history(String lat, String lnt) {
        this.lat = lat;
        this.lnt = lnt;
    }
}
