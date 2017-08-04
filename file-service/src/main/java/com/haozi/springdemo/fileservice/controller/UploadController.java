package com.haozi.springdemo.fileservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haozi.springdemo.fileservice.config.PathConfig;

@Controller
@RequestMapping("/upload")
public class UploadController
{
	// 上传文件时根据此参数决定第一层子文件夹
	private static final String SUB_PATH = "path";

	// 日期子文件夹格式
	private static final String DATE_FORMAT = "YYYYMMdd";

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	private PathConfig pathConfig;

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping("/singleFile")
	@ResponseBody
	public String singleFile(@RequestParam("img") MultipartFile file, HttpServletRequest request)
	{
		if (file.isEmpty())
			return "false";
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		logger.info("upload single file: {} --> {}", fileName, size);

		String path = getFullPath(getSubPath(request.getParameter(SUB_PATH))) + fileName;
		File dest = new File(path);
		if (!dest.getParentFile().exists())
		{ // 判断文件父目录是否存在
			dest.getParentFile().mkdirs();
		}
		try
		{
			file.transferTo(dest); // 保存文件
			return path.substring(uploadPath.length());
		}
		catch (IllegalStateException e)
		{
			logger.error("upload file exception", e);
			return "false";
		}
		catch (IOException e)
		{
			logger.error("upload file exception", e);
			return "false";
		}
	}

	// 多文件上传
	@RequestMapping(value = "/multiFile")
	@ResponseBody
	public Map<String, String> multiFile(HttpServletRequest request)
	{
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		Map<String, String> results = new HashMap<String, String>();
		for (MultipartFile file : files)
		{
			results.put(file.getOriginalFilename(), this.singleFile(file, request));
		}

		return results;
	}

	// 根据日期获取上传路径，以文件分隔符结尾
	private String getFullPath(String subPath)
	{
		DateTime dateTime = new DateTime();
		StringBuffer sb = new StringBuffer(uploadPath);
		if (!uploadPath.endsWith(File.separator))
			sb.append(File.separator);
		if (!StringUtils.isEmpty(subPath))
			sb.append(subPath).append(File.separator);
		sb.append(Integer.toString(dateTime.getYear())).append(File.separator).append(dateTime.getMonthOfYear())
				.append(File.separator).append(dateTime.toString(DATE_FORMAT)).append(File.separator);
		return sb.toString();
	}

	// 获取配置里的子文件夹路径
	private String getSubPath(String key)
	{
		return pathConfig.getMap().get(key);
	}

	public static void main(String[] args)
	{
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}
}
