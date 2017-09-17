
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/uploadcontroller.do")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final String SAVE_DIR = "uploadFiles";

	public UploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = getServletContext().getRealPath("logos");
		path = path.substring(0, path.indexOf(".metadata"));
		path = path + "upload\\webcontent\\logos\\";
		File fileSaveDir = new File(path);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		FileItemFactory d = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(d);
		String nomeDoArquivo = "";
		try {
			List<FileItem> lstFile = upload.parseRequest(request);
			for (FileItem f : lstFile) {
				if (f.isFormField() == false) {
					File file = new File(path + f.getName());
					f.write(file);
					nomeDoArquivo = f.getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String msg = nomeDoArquivo;
		request.setAttribute("msg", msg);
		RequestDispatcher home = request.getRequestDispatcher("./");
		home.forward(request, response);

	}

}
