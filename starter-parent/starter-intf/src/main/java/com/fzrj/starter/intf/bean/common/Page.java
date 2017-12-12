package com.fzrj.starter.intf.bean.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className:com.fzrj.starter.intf.bean.common.Page
 * @description:泛型分页bean
 * @version:v1.0.0
 * @date:2017年8月22日 上午10:36:46
 * @author:WangHao
 */
public class Page<E>
{
	/**
	 * 默认分页条数
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	// 分页条数
	private int pageSize;

	// 起始条数
	private long startIndex;

	// 总条数
	private long totalCount;

	// 分页数据
	private List<E> data;

	private static final Logger logger = LoggerFactory.getLogger(Page.class);

	/**
	 * @return
	 * @Description:获取起始条数
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:40:53
	 */
	public long getStartIndex() {
		return startIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<E>());
	}

	public Page(int pageSize) {
		this(0, 0, pageSize, new ArrayList<E>());
	}

	public Page(long startIndex, long totalCount, int pageSize, List<E> data) {
		if (pageSize <= 0 || startIndex < 0 || totalCount < 0) {
			logger.error("pageSize <= 0 is {}\tstartIndex < 0 is {}\ttotalSize < 0 is {}", pageSize, startIndex, totalCount);
			throw new IllegalArgumentException("Illegal Arguments to Initiate Page Object");
		}
		this.pageSize = pageSize;
		this.startIndex = startIndex;
		this.totalCount = totalCount;
		this.data = data;
	}

	/**
	 * @return
	 * @Description:获取总条数
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:52:24
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * @return
	 * @Description:获取总分页数
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:52:37
	 */
	public long getTotalPageCount() {
		long pc = totalCount / pageSize;
		return totalCount % pageSize == 0 ? pc : pc + 1;
	}

	/**
	 * @return
	 * @Description:获取分页条数
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:54:30
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setResult(List<E> data) {
		this.data = data;
	}

	public List<E> getResult() {
		return data;
	}

	/**
	 * @return
	 * @Description:获取当前分页号
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:55:25
	 */
	public long getCurrentPageNo() {
		return startIndex / pageSize + 1;
	}

	/**
	 * @return
	 * @Description:是否有下一页
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:55:55
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * @return
	 * @Description:是否有前一页
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:56:12
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * @return
	 * @Description:判断是否为空页
	 * @version:v1.0
	 * @author:WangHao
	 * @date:2017年8月22日 上午10:57:43
	 */
	public boolean isEmpty() {
		return data == null || data.isEmpty();
	}

	public long getEndIndex() {
		long endIndex = getCurrentPageNo() * pageSize - 1;
		return endIndex >= totalCount ? totalCount - 1 : endIndex;
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	@Override
	public String toString()
	{
		return "Page [pageSize=" + pageSize + ", startIndex=" + startIndex + ", totalCount=" + totalCount + ", data="
				+ data + "]";
	}
}
