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
import vn.iotstar.entity.*;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categorys", "/admin/category/insert", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118185900565619848L;
	public ICategoryService cateservice = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("insert")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.getRequestDispatcher("/views/admin/categoryInsert.jsp").forward(req, resp);
		} else if (url.contains("categorys")) {
			List<Category> list = cateservice.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/categoryList.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category cate = cateservice.findById(id);
			req.setAttribute("cate", cate);
			req.getRequestDispatcher("/views/admin/categoryUpdate.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				Category cateold = new Category();
				cateold = cateservice.findById(Integer.parseInt(id));
				// xoa hinh cu
				if (!cateold.getImages().substring(0,5).equals("https"))
				{
					deletepath("D:\\Lap-trinh-web\\upload" + "\\" + cateold.getImages());
				}
				cateservice.delete(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("image");
			String active = req.getParameter("active");
			Category cate = new Category();
			cate.setCategoryname(categoryname);

			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
									
										
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setImages(fname);
				} else {
					cate.setImages("defaul.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cate.setStatus(active.equals("true") ? 1 : 0);
			cateservice.insert(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("cateid"));
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("image");
			String active = req.getParameter("active");
			Category cateold = new Category();
			cateold = cateservice.findById(id);
			
			Category cate = new Category();
			cate.setCategoryid(id);
			cate.setCategoryname(categoryname);
			cate.setImages(image);
			cate.setStatus(active.equals("true") ? 1 : 0);
			
			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					
					// xoa hinh cu
					if (!cateold.getImages().substring(0,5).equals("https"))
					{
						deletepath("D:\\Lap-trinh-web\\upload" + "\\" + cateold.getImages());
					}				
					
					
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setImages(fname);
				} else {
					cate.setImages(cateold.getImages());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateservice.update(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}

	}
	private static void deletepath(String filePath) throws IOException
	{
		Path path = Paths.get(filePath);
		 Files.delete(path);
	}
	

}
