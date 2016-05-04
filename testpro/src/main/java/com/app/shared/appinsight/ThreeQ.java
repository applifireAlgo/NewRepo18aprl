package com.app.shared.appinsight;
import com.athena.server.query.bizService.DTOMapperInterface;

public class ThreeQ implements DTOMapperInterface {

    private String ttnm;

    private String nnno;

    private String gen;

    public ThreeQ(Object[] aryObject) {
        if (aryObject != null) {
            ttnm = (java.lang.String) aryObject[0];
            nnno = (java.lang.String) aryObject[1];
            gen = (java.lang.String) aryObject[2];
        } else {
            ttnm = null;
            nnno = null;
            gen = null;
        }
    }

    public String getTtnm() {
        return ttnm;
    }

    public String getNnno() {
        return nnno;
    }

    public String getGen() {
        return gen;
    }
}
