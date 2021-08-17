/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.es;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xuleyan
 * @version Test.java, v 0.1 2021-05-12 3:45 下午
 */
public class Test {

    public static Map<String, String> key2NameMap = new HashMap<>();

    static {

        key2NameMap.put("share_register_document_v100", "健康文档注册");
        key2NameMap.put("main_index_document_register_notify_v100", "文档发布与通知");
        key2NameMap.put("share_docs_retrieve_documentset_v100", "获取健康文档");
        key2NameMap.put("share_docs_register_documentset_v100", "健康文档注册");
        key2NameMap.put("share_docs_update_documentset_v100", "健康文档更新");
        key2NameMap.put("share_docs_document_storedquery_v100", "健康文档检索");

        key2NameMap.put("share_docs_retrieve_valueset_v100", "值集获取");
        key2NameMap.put("share_docs_valueset_query_v100", "查询值集");
        key2NameMap.put("share_docs_retrieve_conceptmap_v100", "获取值集映射");
        key2NameMap.put("share_docs_conceptmap_query_v100", "查询值集映射");

        key2NameMap.put("share_docs_subscribe_v100", "订阅");
        key2NameMap.put("share_docs_unsubscribe_v100", "取消订阅");
        key2NameMap.put("share_docs_pause_subscription_v100", "暂停订阅");
        key2NameMap.put("share_docs_resume_subscription_v100", "恢复订阅");
        key2NameMap.put("share_docs_notify_v100", "订阅通知");
        key2NameMap.put("share_docs_create_pullpoint_v100", "创建拉取点");
        key2NameMap.put("share_docs_destroy_pullpoint_v100", "删除拉取点");
        key2NameMap.put("share_docs_get_messages_v100", "拉取通知");

        key2NameMap.put("main_data_organization_feed_v100", "机构注册");
        key2NameMap.put("main_data_organization_query_v100", "机构查询");
        key2NameMap.put("main_data_person_record_feed_v100", "居民注册");
        key2NameMap.put("main_index_person_record_revise_v100", "居民变更通知");
        key2NameMap.put("main_data_person_record_query_v100", "居民查询");
        key2NameMap.put("main_data_person_identifier_query_v100", "根据交叉索引查询");
        key2NameMap.put("mian_data_id_modify_v100", "主索引修改通知");
        key2NameMap.put("main_data_resident_id_merge_v100", "主索引合并通知");
        key2NameMap.put("main_data_practitioner_feed_v100", "医疗人员注册");
        key2NameMap.put("main_index_practitioner_revise_v100", "医疗人员变更通知");
        key2NameMap.put("main_index_practitioner_query_v100", "医疗人员查询");
    }
}