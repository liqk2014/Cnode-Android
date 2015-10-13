package com.sk.cnode.android.model.api;


import com.sk.cnode.android.model.entity.LoginInfo;
import com.sk.cnode.android.model.entity.Notification;
import com.sk.cnode.android.model.entity.Result;
import com.sk.cnode.android.model.entity.TabType;
import com.sk.cnode.android.model.entity.Topic;
import com.sk.cnode.android.model.entity.TopicUpInfo;
import com.sk.cnode.android.model.entity.TopicWithReply;
import com.sk.cnode.android.model.entity.User;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


public interface ApiService {

    //=====
    // 主题
    //=====

    @GET("v1/topics")
    Call<Result<List<Topic>>> getTopics( );

    @GET("/v1/topic/{id}")
    Call<List<TopicWithReply>> getTopic(
            @Path("id") String id,
            @Query("mdrender") Boolean mdrender
    );

    @FormUrlEncoded
    @POST("/v1/topics")
    Call<Void> newTopic(
            @Field("accesstoken") String accessToken,
            @Field("tab") TabType tab,
            @Field("title") String title,
            @Field("content") String content
    );
    //
    @FormUrlEncoded
    @POST("/v1/topic/{topicId}/replies")
    Call<Map<String, String>> replyTopic(
            @Field("accesstoken") String accessToken,
            @Path("topicId") String topicId,
            @Field("content") String content,
            @Field("reply_id") String replyId
    );

    @FormUrlEncoded
    @POST("/v1/reply/{replyId}/ups")
    Call<TopicUpInfo> upTopic(
            @Field("accesstoken") String accessToken,
            @Path("replyId") String replyId
    );

    //=====
    // 用户
    //=====

    @GET("/v1/user/{loginName}")
    Call<User> getUser(
            @Path("loginName") String loginName
    );

    @FormUrlEncoded
    @POST("/v1/accesstoken")
    Call<LoginInfo> accessToken(
            @Field("accesstoken") String accessToken
    );

    //=========
    // 消息通知
    //=========

    @GET("/v1/message/count")
    Call<Integer> getMessageCount(
            @Query("accesstoken") String accessToken
    );

    @GET("/v1/messages")
    Call<Notification> getMessages(
            @Query("accesstoken") String accessToken
    );

    @FormUrlEncoded
    @POST("/v1/message/mark_all")
    Call<Void> markAllMessageRead(
            @Field("accesstoken") String accessToken
    );

}
