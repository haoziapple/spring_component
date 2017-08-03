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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/upload")
public class UploadController
{
	@Value("${file.upload.path}")
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping("/singleFile")
	@ResponseBody
	public String singleFile(@RequestParam("fileName") MultipartFile file)
	{
		if (file.isEmpty())
			return "false";
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		logger.info("upload single file: {} --> {}", fileName, size);

		String path = getFullPath() + fileName;
		File dest = new File(path);
		if (!dest.getParentFile().exists())
		{ // 判断文件父目录是否存在
			dest.getParentFile().mkdirs();
		}
		try
		{
			file.transferTo(dest); // 保存文件
			return path;
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			return "false";
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
			results.put(file.getOriginalFilename(), this.singleFile(file));
		}

		return results;
	}

	// 根据日期获取上传路径，以文件分隔符结尾
	private String getFullPath()
	{
		DateTime dateTime = new DateTime();
		StringBuffer sb = new StringBuffer(uploadPath);
		if (!uploadPath.endsWith(File.separator))
			sb.append(File.separator);
		sb.append(Integer.toString(dateTime.getYear())).append(File.separator).append(dateTime.getMonthOfYear())
				.append(File.separator).append(dateTime.toString("YYYYMMdd")).append(File.separator);
		return sb.toString();
	}

	public static void main(String[] args)
	{
		UploadController test = new UploadController();
		System.out.println(test.getFullPath());
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}
}
