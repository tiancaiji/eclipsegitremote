package org.student.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//上传
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory() ;
				ServletFileUpload upload = new ServletFileUpload(factory);
				//通过parseRequest解析form中的所有请求字段，并保存到items集合中
			
				//设置上传文件时用到的临时文件的大小
				factory.setSizeThreshold(10240);
				factory.setRepository(new File("E:\\JAVA项目文件\\uploadtemp"));
				//控制上传单个文件的大小
				upload.setSizeMax(20480);
				Thread.sleep(3000);
				
				
				
				List<FileItem> items = upload.parseRequest(request);
				
				Iterator<FileItem> iterator = items.iterator();
				while(iterator.hasNext()) {
					FileItem item = iterator.next();
					String itemName = item.getFieldName();
					int sno = -1;
					String sname = null;
					//判断前台字段是普通form表单字段还是文件字段
					if(item.isFormField()) {
						if(itemName.equals("sno")) {
							sno = Integer.parseInt(item.getString("UTF-8"));
						}else if(itemName.equals("sname")) {
							sname = item.getString("UTF-8");
						}else {
							System.out.println("其他字段");
						}
					}else {
						//文件上传
						//文件名getFieldName是获取普通表单字段的Name值
						//getName（）是获取文件名
						String fileName = item.getName();
						String ext = fileName.substring(fileName.indexOf(".")+1);
						if(!(ext.equals("png")||ext.equals("zip")||ext.equals("jpg"))) {
							System.out.println("图片类型有误");
							return;
						}
						//获取文件内容并上传
						//定义文件路径：指定上传的位置（服务器路径）
						//String path = request.getSession().getServletContext().getRealPath("upload");
						String path = "E:\\JAVA项目文件\\upload";
						File file = new File(path,fileName);
						
						item.write(file);
						System.out.println(fileName+"上传成功");	
						return;
					}
				}
			}
			}catch (FileUploadBase.SizeLimitExceededException e) {
				System.out.println("上传文件大小超过限制");
			}catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
