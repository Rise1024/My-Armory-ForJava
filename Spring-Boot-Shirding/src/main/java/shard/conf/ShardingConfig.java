package com.easemob.weichat.shard.conf;


import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.infra.yaml.config.swapper.mode.ModeConfigurationYamlSwapper;
import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.apache.shardingsphere.spring.boot.datasource.DataSourceMapSetter;
import org.apache.shardingsphere.spring.boot.prop.SpringBootPropertiesConfiguration;
import org.apache.shardingsphere.spring.boot.rule.LocalRulesCondition;
import org.apache.shardingsphere.spring.boot.schema.DatabaseNameSetter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;


@Configuration
@ComponentScan({"org.apache.shardingsphere.spring.boot.converter"})
@EnableConfigurationProperties({SpringBootPropertiesConfiguration.class})
@ConditionalOnProperty(
        prefix = "spring.shardingsphere",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)

@AutoConfigureAfter(ShardingSphereAutoConfiguration.class)
public class ShardingConfig implements EnvironmentAware {

    private final SpringBootPropertiesConfiguration props;
    private final Map<String, DataSource> dataSourceMap = new LinkedHashMap();
    private String schemaName;

    public ShardingConfig(SpringBootPropertiesConfiguration props) {
        this.props = props;
    }

    @Bean
    public ModeConfiguration modeConfiguration() {
        return null == this.props.getMode() ? null : (new ModeConfigurationYamlSwapper()).swapToObject(this.props.getMode());
    }

    @Bean
    @Conditional(LocalRulesCondition.class)
    @Autowired(required = false)
    public DataSource shardingSphereDataSource(final ObjectProvider<List<RuleConfiguration>> rules, final ObjectProvider<ModeConfiguration> modeConfig) throws SQLException {


        Collection<RuleConfiguration> ruleConfigs = Optional.ofNullable(rules.getIfAvailable()).orElse(Collections.emptyList());
        return ShardingSphereDataSourceFactory.createDataSource(schemaName, modeConfig.getIfAvailable(), dataSourceMap, ruleConfigs, props.getProps());
    }

    @Override
    public final void setEnvironment(Environment environment) {
        this.dataSourceMap.putAll(DataSourceMapSetter.getDataSourceMap(environment));
        this.schemaName = DatabaseNameSetter.getDatabaseName(environment);
    }

    /**
     * 通过yaml配置读取分片规则
     * @param shardingRuleConfiguration
     * @return
     * @throws SQLException
     */
    @Primary
    @Bean
    public DataSource dataSource(RuleConfiguration shardingRuleConfiguration) throws SQLException {
        //这里调用ShardingSphereDataSourceFactory.createDataSource方法去创建最终datasource对象。
        return !this.dataSourceMap.isEmpty() ? ShardingSphereDataSourceFactory.createDataSource(this.schemaName, modeConfiguration(), this.dataSourceMap, Arrays.asList(shardingRuleConfiguration), this.props.getProps()) : ShardingSphereDataSourceFactory.createDataSource(this.schemaName, modeConfiguration());
    }

//    /**
//     * 通过硬编码创建分片规则
//     * @param shardingRuleConfiguration
//     * @return
//     * @throws SQLException
//     */
//    @Primary
//    @Bean
//    public DataSource dataSource() {
//
//        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
//        //type为MOD，
//        ShardingSphereAlgorithmConfiguration algorithmConfiguration = new ShardingSphereAlgorithmConfiguration("MOD", this.props.getProps());
//
//        ShardingTableRuleConfiguration shardingTableRuleConfiguration = new ShardingTableRuleConfiguration("sys_user", "master.sys_user_${[0,1]}");
//        shardingTableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "idModShardingAlgorithm"));
//        shardingRuleConfiguration.getTables().add(shardingTableRuleConfiguration);
//        shardingRuleConfiguration.getBindingTableGroups().add("sys_user");
//        shardingRuleConfiguration.getShardingAlgorithms().put("idModShardingAlgorithm", algorithmConfiguration);
//        try {
//
//            ArrayList arrayList = new ArrayList();
//            arrayList.add(shardingRuleConfiguration);
//            return !this.dataSourceMap.isEmpty() ? ShardingSphereDataSourceFactory.createDataSource(this.schemaName, modeConfiguration(), this.dataSourceMap, arrayList, this.props.getProps()) : ShardingSphereDataSourceFactory.createDataSource(this.schemaName, modeConfiguration());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
