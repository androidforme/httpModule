package com.zkzz.module.http.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {

    /**
     * id : 46
     * username : sun
     * telephone :
     * profile : {"portrait":null,"avatar":null,"gender":null,"age":null,"school":null,"grade":null,"away_time":null,"reach_time":null,"subject_press":[{"subject_id":1,"subject_name":"英语","press_id":16,"sap_id":9,"press_name":"鲁教版"}]}
     */

    public int id;

    public String username;

    public String telephone;

    public ProfileBean profile;


    public static class ProfileBean {
        /**
         * portrait : null
         * avatar : null
         * gender : null
         * age : null
         * school : null
         * grade : null
         * away_time : null
         * reach_time : null
         * subject_press : [{"subject_id":1,"subject_name":"英语","press_id":16,"sap_id":9,"press_name":"鲁教版"}]
         */

        public String portrait;

        public String avatar;

        public int gender;

        public int age;

        public String school;

        public Object grade;

        @SerializedName("away_time")
        public Object awayTime;

        @SerializedName("reach_time")
        public Object reachTime;

        @SerializedName("subject_press")
        public List<SubjectPressBean> subjectPress;


        public static class SubjectPressBean {
            /**
             * subject_id : 1
             * subject_name : 英语
             * press_id : 16
             * sap_id : 9
             * press_name : 鲁教版
             */

            @SerializedName("subject_id")
            public int subjectId;

            @SerializedName("subject_name")
            public String subjectName;

            @SerializedName("press_id")
            public int pressId;

            @SerializedName("sap_id")
            public int sapId;
            
            @SerializedName("press_name")
            public String pressName;

        }
    }
}
