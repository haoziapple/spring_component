package com.haozi.springdemo.fileservice.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
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

import com.haozi.springdemo.fileservice.ImgHandler;
import com.haozi.springdemo.fileservice.config.PathConfig;
import com.haozi.springdemo.fileservice.util.RandomUtil;

@Controller
@RequestMapping("/upload")
public class UploadController
{
	private static final String FALSE = "false";

	// 文件名中的随机数位数
	private static final int RANDOM_NUM = 6;

	// 文件名中的日期字串格式
	private static final String FILE_FORMAT = "YYMMddHHmmssSSS";

	// 文件后缀名分隔符
	private static final String SPLIT = ".";

	// 上传文件时根据此参数决定第一层子文件夹
	private static final String SUB_PATH = "path";

	// 日期子文件夹格式
	private static final String DATE_FORMAT = "YYYYMMdd";

	// 上传根目录
	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	private PathConfig pathConfig;

	@Autowired
	private ImgHandler imgHandler;

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping("/singleFile")
	@ResponseBody
	public String singleFile(@RequestParam("img") MultipartFile file, HttpServletRequest request)
	{
		if (file.isEmpty())
			return FALSE;
		// 原文件名
		String orignFileName = file.getOriginalFilename();
		// 文件后缀名
		String suffix = orignFileName.substring(orignFileName.lastIndexOf(SPLIT));
		// 最终文件名
		String fileName = this.getFileName() + suffix;
		int size = (int) file.getSize();
		logger.info("upload single file: {} --> {}", fileName, size);

		// 获取输出文件路径
		String path = getFullPath(getSubPath(request.getParameter(SUB_PATH))) + fileName;
		File dest = new File(path);
		// 判断文件父目录是否存在
		if (!dest.getParentFile().exists())
		{
			dest.getParentFile().mkdirs();
		}
		try
		{
			file.transferTo(dest); // 保存文件
			// 处理图片
			BufferedImage image = ImageIO.read(dest);
			if (image != null)
				imgHandler.processImg(path, suffix);
			// 根目录的路径截取掉，不需要返回
			return path.substring(uploadPath.length());
		}
		catch (IllegalStateException | IOException e)
		{
			logger.error("upload file exception", e);
			return FALSE;
		}
	}

	// 多文件上传
	@RequestMapping(value = "/multiFile")
	@ResponseBody
	public Map<String, String> multiFile(HttpServletRequest request)
	{
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		Map<String, String> results = new HashMap<>();
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
		StringBuilder sb = new StringBuilder(uploadPath);

		// subPath为业务相关的分目录
		// subPath下按年/月/日期的三级分目录进行文件存放
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

	// 生成图片文件名称（规则：6位随机数+时间）
	private String getFileName()
	{
		DateFormat df1 = new SimpleDateFormat(FILE_FORMAT);
		return RandomUtil.randomNum(RANDOM_NUM) + df1.format(new Date());
	}

	public static void main(String[] args)
	{
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
		System.out.println(RandomStringUtils.random(6, "0123456789"));
	}
}
