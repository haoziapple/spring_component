package com.fzrj.starter.service.sandbox;

/**
 * Created by Administrator on 2017/11/28.
 */
public abstract class GlobalConfigStorage {
    private static volatile Brand brand = null;

    public static Brand getBrand() {
        if (brand == null) {
            brand = Brand.fromString(SystemConfigUtil.getBrand());
        }
        return brand;
    }

    private static volatile String projectName = null;

    public static String getProjectName() {
        return projectName;
    }

    public static void saveProjectName(String currentProjectName) {
        projectName = currentProjectName;
    }

    private static volatile String configurationDirectory = null;

    public static String getConfigurationDirectory() {
        return configurationDirectory;
    }

    public static void setConfigurationDirectory(String configurationDirectory) {
        GlobalConfigStorage.configurationDirectory = configurationDirectory;
    }
}
