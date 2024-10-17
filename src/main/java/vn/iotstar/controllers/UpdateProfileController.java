package vn.iotstar.controllers;

import java.io.File;
import java.io.FileNotFoundException;
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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.services.UserService;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IUserService;
import vn.iotstar.ultis.Constant;

@MultipartConfig()
@WebServlet(urlPatterns = {"/updateProfile"})
public class UpdateProfileController extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		if (username != null) {
			UserService userService = new UserService();
			User user = userService.findByUserName(username);
			req.setAttribute("account", user);
			req.getRequestDispatcher("/views/updateProfile.jsp").forward(req, resp);    	 
           
       } else {
           resp.sendRedirect(req.getContextPath() + "/login");
       }		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		User user = null;
		if (session != null && session.getAttribute("account") != null) {
			user = (User) session.getAttribute("account");		
			
			// lấy dữ liệu từ form
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");
			String images = req.getParameter("images1");
			// đưa dữ liệu vào model
			
			String fileold = user.getAvatar();
			user.setFullname(fullname);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPassword(password);
			
			String fname = "";
			
			//xu ly image
			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục
					if (!user.getAvatar().substring(0, 5).equals("https")) {
						deleteFile(uploadPath + "\\" + fileold);
					}
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					user.setAvatar(fname);
				} else if (images != null) {
					user.setAvatar(images);
				} else {
					user.setAvatar(fileold);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			}
			
			IUserService service = new UserService();
			service.update(user);			
			
			resp.sendRedirect(req.getContextPath() + "/profile"); // Chuyển về trang profile sau khi cập nhật
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/login"); 
		}
	}
	
	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}
