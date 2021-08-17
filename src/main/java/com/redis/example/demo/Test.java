/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuleyan
 * @version Test.java, v 0.1 2020-12-20 9:11 下午
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.start();
    }

    private void start() {
//        Result result = new Result();

        Map<String, Object> result = new HashMap<>();
        MatchedInfo matchedInfo = new MatchedInfo();

        RecommendInfo recommendInfo = new RecommendInfo();
        recommendInfo.setRecommendAccount("current");
        recommendInfo.setRecommendText("到款到网商银行得5.88");
        matchedInfo.recommendInfo = recommendInfo;
        result.put("matchedInfo", matchedInfo);

        MatchedInfo matchedInfo1 = (MatchedInfo) result.get("matchedInfo");


        Map<String, String> map = new HashMap<>();
        map.put("name", "value");
        map.put("name2", "value2");
        map.put("name3", "value3");
        result.put("map", map);

        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        result.put("list", list);

        System.out.println(result);

        //{
        //	matchedInfo=RecommendInfo
        //	{
        //		recommendAccount='current',
        //		recommendText='到款到网商银行得5.88'
        //	},
        //	list=[
        //		{
        //			name3=value3,
        //			name=value,
        //			name2=value2
        //		}
        //	],
        //	map={
        //		name3=value3,
        //		name=value,
        //		name2=value2
        //	}
        //}
    }

    class Result {
        private boolean success = true;

        private boolean retry = false;

        private MatchedInfo matchedInfo = new MatchedInfo();

        @Override
        public String toString() {
            return "Result{" +
                    "success=" + success +
                    ", retry=" + retry +
                    ", matchedInfo=" + matchedInfo +
                    '}';
        }
    }

    class MatchedInfo {
        private RecommendInfo recommendInfo = new RecommendInfo();

        @Override
        public String toString() {
            return "MatchedInfo{" +
                    "recommendInfo=" + recommendInfo +
                    '}';
        }
    }

    class RecommendInfo {
        private String recommendAccount;
        private String recommendText;

        public String getRecommendAccount() {
            return recommendAccount;
        }

        public void setRecommendAccount(String recommendAccount) {
            this.recommendAccount = recommendAccount;
        }

        public String getRecommendText() {
            return recommendText;
        }

        public void setRecommendText(String recommendText) {
            this.recommendText = recommendText;
        }

        @Override
        public String toString() {
            return "RecommendInfo{\n" +
                    "recommendAccount='" + recommendAccount + '\'' +
                    ", recommendText='" + recommendText + '\'' +
                    "\n}" + "\n";
        }
    }
}