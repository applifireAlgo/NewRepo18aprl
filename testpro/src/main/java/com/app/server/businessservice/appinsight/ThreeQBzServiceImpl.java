package com.app.server.businessservice.appinsight;
import org.springframework.stereotype.Component;
import com.athena.server.query.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.appinsight.ThreeQ;
import java.lang.Override;
import java.util.List;

@Component
public class ThreeQBzServiceImpl implements ThreeQBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<ThreeQ> executeQueryThreeQ() throws Exception {
        java.util.List<com.app.shared.appinsight.ThreeQ> listDtoInterface = new java.util.ArrayList<com.app.shared.appinsight.ThreeQ>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "4F6FF483-811A-4F15-B762-7FF6E15C7337");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.appinsight.ThreeQ", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
