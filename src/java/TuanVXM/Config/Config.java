/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Config;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TuanVXM
 */
public class Config {
    private List<ReplaceConfig> replaceConfigs;
    private SingleConfig startConfig;
    private SingleConfig endConfig;
    private List<SingleConfig> configs;

    public Config() {
        replaceConfigs = new ArrayList<>();
        configs = new ArrayList<>();
    }

    public List<ReplaceConfig> getReplaceConfigs() {
        return replaceConfigs;
    }

    public void setReplaceConfigs(List<ReplaceConfig> replaceConfigs) {
        this.replaceConfigs = replaceConfigs;
    }

    public SingleConfig getStartConfig() {
        return startConfig;
    }

    public void setStartConfig(SingleConfig startConfig) {
        this.startConfig = startConfig;
    }

    public SingleConfig getEndConfig() {
        return endConfig;
    }

    public void setEndConfig(SingleConfig endConfig) {
        this.endConfig = endConfig;
    }

    public List<SingleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<SingleConfig> configs) {
        this.configs = configs;
    }
}
