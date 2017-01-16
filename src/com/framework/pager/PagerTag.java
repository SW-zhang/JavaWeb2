package com.framework.pager;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("serial")
public class PagerTag extends TagSupport {
	private int curPage; // 当前页
	private int totalPage; // 总页数
	private int pageSize; // 每页显示条数
	private String ajaxFn;//调用ajax方法
	private String url; // 请求地址
	private String fullUrl;	//全路径
	private Map<String,String[]> params; // 参数信息

	private static final String dotted = "<li class=\"dotted\"><span>...</span></li>"; // 点点...
	
	public int doStartTag() throws JspException {
		initFullUrl();
		StringBuffer uri = new StringBuffer();
		JspWriter out = pageContext.getOut();
		try {
			uri.append("<div class=\"pagination\"><ul>");
			if (totalPage >= 1) {
				uri.append(this.getPrevPage());
				if(totalPage<=10){
					for (int i = 0; i < totalPage; i++) {
						uri.append(this.getPageLink(i));
					}
				}else{
					uri.append(this.getPageLink(0));
					uri.append(this.getPageLink(1));
					uri.append(this.getPageLink(2));
					if(curPage<7){
						uri.append(this.getPageLink(3));
						uri.append(this.getPageLink(4));
						uri.append(this.getPageLink(5));
						uri.append(this.getPageLink(6));
						uri.append(this.getPageLink(7));
						uri.append(dotted);
					}else if(curPage >= 7 && curPage<=(totalPage-7)){
						uri.append(dotted);
						uri.append(this.getPageLink(curPage-2));
						uri.append(this.getPageLink(curPage-1));
						uri.append(this.getPageLink(curPage));
						uri.append(this.getPageLink(curPage+1));
						uri.append(this.getPageLink(curPage+2));
						uri.append(dotted);
					}else if(curPage>(totalPage-7)){
						uri.append(dotted);
						uri.append(this.getPageLink(totalPage-7));
						uri.append(this.getPageLink(totalPage-6));
						uri.append(this.getPageLink(totalPage-5));
						uri.append(this.getPageLink(totalPage-4));
						uri.append(this.getPageLink(totalPage-3));
					}
					uri.append(this.getPageLink(totalPage-2));
					uri.append(this.getPageLink(totalPage-1));
				}
				uri.append(this.getNextPage());
			}
			uri.append("</ul></div>");
			out.print(uri.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return super.doStartTag();
	}
	private void initFullUrl(){
		HttpServletRequest httpServletRequest = (HttpServletRequest) pageContext.getRequest();
		String requestUrl = httpServletRequest.getRequestURL().toString();
		String contextPath = httpServletRequest.getContextPath();
		requestUrl = requestUrl.split(contextPath)[0];
		fullUrl = requestUrl+contextPath+url;
		StringBuffer paramBuffer = new StringBuffer(); 
		if (params!=null&&!params.isEmpty()) {
			for(String paramName : params.keySet()){
				if("page".equals(paramName)){
					continue;
				}
				if("pageSize".equals(paramName)){
					continue;
				}
				for(int i=0;i<params.get(paramName).length;i++){
					if(!StringUtils.isEmpty((params.get(paramName)[i]))){
						paramBuffer.append(paramName+"="+params.get(paramName)[i]+"&");
					}
				}
			}
			if (fullUrl.indexOf("?") < 0) {
				fullUrl = fullUrl + "?" + paramBuffer.toString();
			} else {
				fullUrl = fullUrl + "&" + paramBuffer.toString();
			}
		}
		
		if (fullUrl.indexOf("?") < 0) {
			fullUrl = fullUrl + "?" + (pageSize==10?"":("rows=" + pageSize + "&") ) + "page=";
		} else {
			fullUrl = fullUrl + (pageSize==10?"":("rows=" + pageSize + "&") ) + "page=";
		}
	}
	private String getPageLink(int targetPageNo) {
		if(curPage == targetPageNo){
			return "<li class=\"active\"><span>" + (targetPageNo+1) + "</span></li>";
		}else{
			if(isAjax()){
				return "<li><a href=\"" + fullUrl + targetPageNo + "\" onclick=\""+ajaxFn+"("+targetPageNo+");return false;\" >" + (targetPageNo+1) + "</a></li>";
			}else{
				return "<li><a href=\"" + fullUrl + targetPageNo + "\" >" + (targetPageNo+1) + "</a></li>";
			}
			
		}
	}
	private String getPrevPage(){
		if (curPage <= 0) {
			return "<li class=\"disabled\"><span>&lt;</span></li>";
		} else {
			if(isAjax()){
				return "<li><a href=\"" + fullUrl + (curPage-1) + "\" onclick=\""+ajaxFn+"("+(curPage-1)+");return false;\" >&lt;</a></li>";
			}else{
				return "<li><a href=\"" + fullUrl + (curPage-1) + "\" >&lt;</a></li>";  
			}
		}
	}
	private String getNextPage(){
		if (curPage==totalPage-1) {
			return "<li class=\"disabled\"><span>&gt;</span></li>";
		} else {
			if(isAjax()){
				return "<li><a href=\"" + fullUrl + (curPage+1) + "\" onclick=\""+ajaxFn+"("+(curPage+1)+");return false;\" >&gt;</a></li>";
			}else{
				return "<li><a href=\"" + fullUrl + (curPage+1) + "\" >&gt;</a></li>";  
			}
		}
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String[]> getParams() {
		return params;
	}
	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}
	public String getAjaxFn() {
		return ajaxFn;
	}
	public void setAjaxFn(String ajaxFn) {
		this.ajaxFn = ajaxFn;
	}
	public void release() {
		curPage = 0; // 当前页
		totalPage = 0; // 总页数
		pageSize = 0; // 每页显示条数
		url = null; // 请求地址
		params = null; // 参数信息
		ajaxFn = null;	
		super.release();
	}
	private boolean isAjax(){
		return ajaxFn!=null&&!"".equals(ajaxFn);
	}
}
