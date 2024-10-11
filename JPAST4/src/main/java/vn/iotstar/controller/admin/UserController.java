package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.UserServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

maxFileSize = 1024 * 1024 * 5,

maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/users", "/admin/user/insert", "/admin/user/update",
"/admin/user/delete"})
public class UserController  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService us = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("insert")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.getRequestDispatcher("/views/admin/userInsert.jsp").forward(req, resp);
		} else if (url.contains("users")) {
			List<User> list = us.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/userList.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = us.findById(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/views/admin/userUpdate.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				User userold = new User();
				userold = us.findById(Integer.parseInt(id));
				// xoa hinh cu
				if (!userold.getImages().substring(0,5).equals("https"))
				{
					deletepath("D:\\Lap-trinh-web\\upload" + "\\" + userold.getImages());
				}
				us.delete(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
			String name = req.getParameter("name");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String status = req.getParameter("status");
			
			User user = new User();
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setStatus(status.equals("true") ? 1 : 0);
			
			
			
			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
									
										
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					user.setImages(fname);
				} else {
					user.setImages("defaul.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			us.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("userid"));
			String name = req.getParameter("name");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String status = req.getParameter("status");
			
			User user = new User();
			user.setUserid(id);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setStatus(status.equals("true") ? 1 : 0);
			
			
			User userold = us.findById(id);
			
			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					
					// xoa hinh cu
					if (!userold.getImages().substring(0,5).equals("https"))
					{
						deletepath("D:\\Lap-trinh-web\\upload" + "\\" + userold.getImages());
					}				
					
					
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					user.setImages(fname);
				} else {
					user.setImages(userold.getImages());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			us.update(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}
	private static void deletepath(String filePath) throws IOException
	{
		try {
		Path path = Paths.get(filePath);
		 Files.delete(path);
		}catch (Exception e) {
			
		}
	}
}
