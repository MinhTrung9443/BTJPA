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
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IVideoService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.service.impl.VideoServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

maxFileSize = 1024 * 1024 * 5,

maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/insert", "/admin/video/update",
"/admin/video/delete", "/admin/video/search" })
public class VideoController extends HttpServlet{
	public IVideoService videoservice = new VideoServiceImpl();
	public ICategoryService cateserv = new CategoryServiceImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("insert")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
//			List<Category> categoryList = cateserv.findAll();
//			req.setAttribute("categoryList", categoryList);
			req.getRequestDispatcher("/views/admin/videoInsert.jsp").forward(req, resp);
		} else if (url.contains("videos")) {
			List<Video> list = videoservice.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/VideoList.jsp").forward(req, resp);
		} else if (url.contains("update")) {
//			List<Category> categoryList = cateserv.findAll();
//			req.setAttribute("categoryList", categoryList);
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoservice.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/videoUpdate.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			
			try {
				Video video = new Video();
				video = videoservice.findById(Integer.parseInt(id));
				// xoa hinh cu
				if (!(video.getPoster()==null) && !video.getPoster().substring(0,5).equals("https"))
				{
					deletepath("D:\\Lap-trinh-web\\upload" + "\\" + video.getPoster());
				}
				System.out.println(video.getVideoid());
				videoservice.delete(video.getVideoid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/videoSearch.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Video video= null; 
		if (url.contains("insert")) {
			video= new Video();
			String title = req.getParameter("videotitle");
			String description = req.getParameter("videodescription");
			String poster = req.getParameter("poster");
			String views = req.getParameter("videoviews");
			String active = req.getParameter("active");

			
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(Integer.parseInt(views));
			video.setActive(active.equals("true"));

			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
											
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
				} else {
					video.setPoster("defaul.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			videoservice.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} 
		else if (url.contains("update")) {
			video= new Video();
			int id = Integer.parseInt(req.getParameter("videoid"));
			
			Video videoold = new Video();
			videoold = videoservice.findById(id);
			
			String title = req.getParameter("videotitle");
			String description = req.getParameter("videodescription");
			String views = req.getParameter("videoviews");
			String active = req.getParameter("active");

			
			video.setVideoid(id);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(Integer.parseInt(views));
			video.setActive(active.equals("true"));

			
			String fname = "";
			String uploadPath = "D:\\Lap-trinh-web\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
			
				if (part.getSize() > 0) {
											
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setPoster(fname);
				} else {
					video.setPoster(videoold.getPoster());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			videoservice.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/videoSearch.jsp").forward(req, resp);
		}
	}
	private static void deletepath(String filePath) throws IOException
	{
		Path path = Paths.get(filePath);
		 Files.delete(path);
	}
}
