package com.fzrj.starter.service.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载系统配置参数
 */
public class SystemConfigUtil {
    private static Logger logger = LoggerFactory.getLogger(SystemConfigUtil.class);

    private static final String JBOSS_SERVER_CONFIG_DIR = "jboss.server.config.dir";

    private static final String CONFIGURATION_FILE_PATH = "configuration.file.path";

    private static final String BRAND = "brand";

    private static Properties props = new Properties();

    private static void init() {
        try {
            // 获取JBOSS配置路径
            String jbossConfigPath = System.getProperty(JBOSS_SERVER_CONFIG_DIR);
            if (!StringUtils.hasLength(jbossConfigPath)) {
                initByClassPath();
            } else {
                try {
                    String filePath = jbossConfigPath + File.separator + "ec.properties";
                    File file = new File(filePath);
                    InputStream in = new FileInputStream(file);
                    try {
                        props.load(in);
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }
                } catch (Exception e) {
                    logger.warn("获取ec.properties失败，读取war文件！errorMsg={}", e.getMessage());
                    initByClassPath();
                }
            }
        } catch (Exception e) {
            logger.error("获取ec.properties失败！errorMsg={}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static void initByClassPath() throws IOException {
        // 获取JBOSS配置路径
        ClassPathResource classPathResource = new ClassPathResource("/ec.properties", SystemConfigUtil.class);
        InputStream in = classPathResource.getInputStream();
        try {
            props.load(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }

    }

    public static final String getBrand() {
        if (props.isEmpty()) {
            init();
        }
        return props.getProperty(BRAND);
    }

    public static final String getConfigurationFilePath() {
        if (props.isEmpty()) {
            init();
        }
        return props.getProperty(CONFIGURATION_FILE_PATH);
    }

    public static final String get(String Key) {
        if (props.isEmpty()) {
            init();
        }
        return props.getProperty(Key);
    }
}
