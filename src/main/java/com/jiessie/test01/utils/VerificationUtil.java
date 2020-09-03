package com.jiessie.test01.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class VerificationUtil {

    public String authPrams(String accountNo, String secretCode, Long timestamp, Object paramObj) {

        String signCode = null;
        Map<String, Object> requestMap = new HashMap<>();
        List<String> paramList = new ArrayList<>();

        try {
            requestMap.put("accountNo", accountNo);
            requestMap.put("secretCode", secretCode);
            requestMap.put("timestamp", timestamp);
            requestMap.put("data", paramObj != null ? paramObj : new HashMap<String, Object>());
            String preAuthStr = JSONObject.toJSONString(requestMap);
            JSONObject requestJ = JSONObject.parseObject(preAuthStr);
            paramObj2ParamList(null, requestJ, paramList);

            Collections.sort(paramList);
            String paramCode = StringUtils.join(paramList, "&");
            signCode = StringUtil.md5(paramCode);

            return signCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signCode;
    }

    private void paramObj2ParamList(String authKey, Object authValue, List<String> paramList) {

        try {
            if (authValue instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) authValue;
                for (String pkey : jsonObject.keySet()) {
                    paramObj2ParamList(pkey, jsonObject.get(pkey), paramList);
                }

            } else if (authValue instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) authValue;
                for (Object valueObj : jsonArray) {
                    paramObj2ParamList(authKey, valueObj, paramList);
                }

            } else {
                if (authValue != null) {
                    paramList.add(authKey.trim() + "=" + authValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

}
