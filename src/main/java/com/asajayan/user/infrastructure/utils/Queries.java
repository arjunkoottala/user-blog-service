package com.asajayan.user.infrastructure.utils;

public class Queries {
    public final static String unsubscribeQuery = "DELETE FROM user_topic where user_id = ?1 and topic_id = ?2";

    public static final String findTopicByUserId = "Select * from user_topic where user_id = ?1";

    public static final String findUserbyIdQuery = "Select * from user_tbl where id = ?1";
    public static final String findByUserNameQuery = "Select * from user_tbl where user_name = ?1";
    public static final String findByMailIdQuery = "Select * from user_tbl where email = ?1";
}
