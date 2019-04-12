package com.zkzz.module.http.api;

import com.zkzz.module.http.model.BaseRespondModel;
import com.zkzz.module.http.model.ScoreModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 评价接口
 */
public interface ScoresApi {

    /**
     * 评分
     *
     * @param item          #被评分的数据 视频：video_{video_id}; 专辑：cover_{cover_id};客服：service_{id};
     * @param score         #评分，百分制
     * @param granted       #评分发起者 设备：profile_{profile_id} 用户：user_{user_id} 系统：system_{uid}
     * @param description   #描述
     * @param addition_info [{"key":"addition_info","value":"123","description":"#额外信息，以后可用于保存同步课程中评测的题目等信息","type":"text","enabled":true}]
     * @return
     */
    @FormUrlEncoded
    @POST("/users/scores")
    Observable<BaseRespondModel<ScoreModel>> serviceScores(@Field("item") String item,
                                                           @Field("score") int score,
                                                           @Field("granted") String granted,
                                                           @Field("description") String description,
                                                           @Field("addition_info") String addition_info);
}
