package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
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
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.VideoService;
import vn.iotstar.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit", "/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService videoService = new VideoService();
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        if (url.contains("videos")) {
            List<Video> list = videoService.findAll();
            req.setAttribute("listVideos", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
        	List<Category> categories = categoryService.findAll(); 
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            String videoId = req.getParameter("id");
            Video video = videoService.findById(videoId);
            List<Category> categories = categoryService.findAll(); 
            req.setAttribute("categories", categories);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            String videoId = req.getParameter("id");
            try {
                videoService.delete(videoId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
        	String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String view = req.getParameter("views");
            int views = 0;
            if (view != null) {
            	views = Integer.parseInt(view);
            }
            String description = req.getParameter("description");
            String Active = req.getParameter("active");
            Boolean active;
            if (Active != "1") {
            	active = false;
            }
            else {
            	active = true;
            }
            
            String poster = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    poster = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + poster);
                } else {
                    poster = "default-poster.png"; // Default poster if none is uploaded
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String categoryId = req.getParameter("categoryid");
            System.out.print(categoryId);
            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setViews(views);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);
   
            Category category = new Category();
            category.setCategoryid(Integer.parseInt(categoryId));
            video.setCategory(category);
            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("update")) {
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String Active = req.getParameter("active");
            boolean active;
            if (Active == null) {
            	active = false;
            }
            else {
            	active = true;
            }
            String poster = "";

            Video video = videoService.findById(videoId);
            String oldPoster = video.getPoster();
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    poster = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + poster);
                } else {
                    poster = oldPoster; // Retain old poster if none is uploaded
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String categoryId = req.getParameter("categoryid");
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active);
            video.setPoster(poster);
            Category category = new Category();
            int id = Integer.parseInt(categoryId);
            category.setCategoryid(id);
            video.setCategory(category);
            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
