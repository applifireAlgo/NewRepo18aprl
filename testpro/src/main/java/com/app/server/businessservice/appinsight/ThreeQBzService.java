package com.app.server.businessservice.appinsight;
import com.app.shared.appinsight.ThreeQ;
import java.util.List;

public interface ThreeQBzService {

    public List<ThreeQ> executeQueryThreeQ() throws Exception;
}
