/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.es;

import com.alijk.bqcommon.base.enums.YesNoMark;
import com.alijk.bqcommon.search.entity.request.EsBaseRequest;
import com.alijk.bqcommon.search.entity.response.EsResult;
import com.alijk.bqcommon.search.store.ElasticSearchStore;
import com.redis.example.demo.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedMin;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author xuleyan
 * @version HsbLogServiceImpl.java, v 0.1 2021-05-11 5:57 下午
 */
@Service
@Slf4j
public class HsbLogServiceImpl implements HsbLogService {

    @Resource
    private ElasticSearchStore elasticSearchStore;

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

    @Override
    public void runLog() {
        log.info("runLog");
        // ('main_data_practitioner_feed_v100', '医护人员注册', '91330110MA2B1M4K5X', '20200222194004428679422934532096', '主数据管理平台', '20210330', '202103', 0, 0, 0, 0, 0, '2021-03-31 00:00:01', NULL),
//        String sqlTemplate = "('%s', '%s', '91330110MA2B1M4K5X', '20200222194004428679422934532096', '主数据管理平台', '%s', '%s', %d, %d, %d, %d, %d, '2021-03-31 00:00:01', NULL), \n\r";
        String sqlTemplate = "UPDATE hsb_app_api_invoke_log_statistic SET invoke_total = %d,error_total = %d, min_rt = %d, max_rt = %d, average_rt = %d WHERE current_day = '%s' AND api_key = '%s'; \n\r";
        // 计算当前时间和2020-11-01之间的统计数据生成
        Date date = DateTimeUtils.parse("2021-04-01", DateTimeUtils.NORMAL_DATE_PATTERN);
        Date dateNow = DateTimeUtils.parse("2021-05-11", DateTimeUtils.NORMAL_DATE_PATTERN);
        StringBuilder sb = new StringBuilder();
        //sb.append("INSERT INTO `hsb`.`hsb_app_api_invoke_log_statistic`(`api_key`, `api_alias_name`, `org_code`, `app_id`, `app_name`, `current_day`, `current_month`, `invoke_total`, `error_total`, `min_rt`, `max_rt`, `average_rt`, `gmt_create`, `remark`) VALUES ");
        while (date.getTime() < dateNow.getTime()) {
            Date startDate = new Date();
            startDate.setTime(date.getTime());
            Date dateEnd = DateTimeUtils.addDate(startDate, 1);
            Map<String, HsbInvokeLogStatisticsInfo> statistics = statistics(29, startDate.getTime(), dateEnd.getTime());
            date = dateEnd;
            for (String key : statistics.keySet()) {
                String startDateStr = DateTimeUtils.format(startDate, DateTimeUtils.DAILY_DATE_PATTERN);
                String startMonth = DateTimeUtils.format(startDate, "yyyyMM");
                HsbInvokeLogStatisticsInfo info = statistics.get(key);
                //String format = String.format(sqlTemplate, key, key2NameMap.get(key), startDateStr, startMonth, info.getTotal(), info.getErrorTotal(), info.getMin(), info.getMax(), info.getAvgRt());
                String format = String.format(sqlTemplate, info.getTotal(), info.getErrorTotal(), info.getMin(), info.getMax(), info.getAvgRt(), startDateStr, key);
                sb.append(format);
            }
        }
        writeResult(sb.toString(), "hulianhutong");
    }

    /**
     * 写入文件和存入数据库
     *
     * @param result
     * @param title
     */
    private void writeResult(String result, String title) {
        File file = new File("target/" + title + ".sql");

        try (
             FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(result);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计一段时间内接口的调用总数，失败总数，最小响应时间，最大响应时间，平均响应时间
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String, HsbInvokeLogStatisticsInfo> statistics(int pageSize, long startTime, long endTime) {
        String apiKeyFiled = "apiKey";
        String costTime = "costTime";
        String serverAppId = "20200222194004428679422934532096";

        String avgCostTimeField = "avgCostTime";
        String minCostTimeField = "minCostTime";
        String maxCostTimeField = "maxCostTime";

        EsBaseRequest request = new EsBaseRequest();
        /* 超时时间设置长一点 */
        request.setTimeout(60 * 60);
        /* 算子配置 */
        List<AggregationBuilder> aggregations = new ArrayList<>(1);
        aggregations.add(AggregationBuilders.terms(apiKeyFiled).field(apiKeyFiled).size(pageSize)
                /* 子算子进行平均数的计算 */
                .subAggregation(AggregationBuilders.avg(avgCostTimeField).field(costTime))
                .subAggregation(AggregationBuilders.min(minCostTimeField).field(costTime))
                .subAggregation(AggregationBuilders.max(maxCostTimeField).field(costTime))
        );

        /* 时间范围 */
        BoolQueryBuilder boolQueryBuilder = boolQueryBuilder(startTime, endTime);
        boolQueryBuilder.must(QueryBuilders.termQuery("serverAppId", serverAppId));
        request.setBuilder(new SearchSourceBuilder().query(boolQueryBuilder));

        request.setPageSize(pageSize);

        request.setAggregations(aggregations);
        request.setIndexs(new String[]{"hsb_api_invoke_log_record"});


        log.info("平均响应时间&总数统计,start:{}~end:{}", new DateTime(startTime).toString(DateTimeUtils.NORMAL_DATE_PATTERN), new DateTime(endTime).toString(DateTimeUtils.NORMAL_DATE_PATTERN));
        long start = System.currentTimeMillis();
        EsResult<Map<String, Map>> search = elasticSearchStore.search(request);
        log.info("平均响应时间&总数统计,start:{}~end:{}, 耗时:[{}ms]",
                new DateTime(startTime).toString(DateTimeUtils.NORMAL_DATE_PATTERN), new DateTime(endTime).toString(DateTimeUtils.NORMAL_DATE_PATTERN), System.currentTimeMillis() - start);

        Aggregations aggResponses = search.getAggResponses();

        if (aggResponses == null) {
            return Collections.EMPTY_MAP;
        }

        Map<String, HsbInvokeLogStatisticsInfo> result = new HashMap<>(pageSize);

        for (Aggregation agg : aggResponses) {
            /* 分组计算后返回的agg响应体类型为 ParsedStringTerms */
            ParsedStringTerms parsedStringTerms = (ParsedStringTerms) agg;
            List<? extends Terms.Bucket> buckets = parsedStringTerms.getBuckets();

            if (buckets == null) {
                continue;
            }

            /* 计算好的内容 第一层为分组信息，每一个apiKey参与计算的doc数 */
            for (Terms.Bucket bucket : buckets) {

                HsbInvokeLogStatisticsInfo invokeLogStatisticsInfo = new HsbInvokeLogStatisticsInfo();
                invokeLogStatisticsInfo.setErrorTotal(0L);
                String apiKey = bucket.getKeyAsString();
                long docCount = bucket.getDocCount();
                invokeLogStatisticsInfo.setApiKey(apiKey);
                invokeLogStatisticsInfo.setTotal(docCount);

                result.put(apiKey, invokeLogStatisticsInfo);

                Aggregations avgAggs = bucket.getAggregations();

                if (avgAggs == null) {
                    continue;
                }

                /* 分组后下的平均数计算 */
                for (Aggregation agg3 : avgAggs) {

                    if (avgCostTimeField.equals(agg3.getName())) {
                        ParsedAvg parsedAvg = (ParsedAvg) agg3;
                        invokeLogStatisticsInfo.setAvgRt((int) parsedAvg.getValue());
                    } else if (maxCostTimeField.equals(agg3.getName())) {
                        ParsedMax parsedMax = (ParsedMax) agg3;
                        invokeLogStatisticsInfo.setMax((int) parsedMax.getValue());
                    } else if (minCostTimeField.equals(agg3.getName())) {
                        ParsedMin parsedMin = (ParsedMin) agg3;
                        invokeLogStatisticsInfo.setMin((int) parsedMin.getValue());
                    }
                }
                log.info("apiKey:[{}],平均响应时间:{},总数统计:{}", apiKey, invokeLogStatisticsInfo.getAvgRt(), docCount);
            }
        }

        /* 查询错误总数 */
        request.setBuilder(new SearchSourceBuilder()
                .query(boolQueryBuilder(startTime, endTime)
                        .must(QueryBuilders.termQuery("serverAppId", serverAppId))
                        .must(QueryBuilders.termQuery("invokeStatus", YesNoMark.N.getCode()))));

        aggregations.clear();
        aggregations.add(AggregationBuilders.terms(apiKeyFiled).field(apiKeyFiled).size(pageSize));
        EsResult<Map<String, Map>> errorTotalResult = elasticSearchStore.search(request);
        Aggregations errorTotalAgg = errorTotalResult.getAggResponses();

        for (Aggregation agg : errorTotalAgg) {
            ParsedStringTerms parsedStringTerms = (ParsedStringTerms) agg;
            List<? extends Terms.Bucket> buckets = parsedStringTerms.getBuckets();

            if (buckets == null) {
                continue;
            }
            for (Terms.Bucket bucket : buckets) {

                String apiKey = bucket.getKeyAsString();
                HsbInvokeLogStatisticsInfo invokeLogStatisticsInfo = result.get(apiKey);
                long docCount = bucket.getDocCount();
                if (invokeLogStatisticsInfo != null) {
                    invokeLogStatisticsInfo.setErrorTotal(docCount);
                }
            }
        }


        return result;
    }

    private BoolQueryBuilder boolQueryBuilder(long startTime, long endTime) {
        return new BoolQueryBuilder()
                .must(QueryBuilders
                        .rangeQuery("requestTime")
                        .from(startTime)
                        .to(endTime));
    }

}