package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name.contains(".jpg")) {
			// Chargement d'une image depuis un emplacement sécurisé du serveur
			BufferedImage image = getImage(request.getRequestURI(), name);
			// Définition de l'en-tête de la réponse
			response.setContentType("image/jpeg");
			// Sérialisation de l'image dans la réponse
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "JPEG", out);
			out.close();
		} else if (name.contains(".png")) {
			// Chargement d'une image depuis un emplacement sécurisé du serveur
			BufferedImage image = getImage(request.getRequestURI(), name);
			// Définition de l'en-tête de la réponse
			response.setContentType("image/png");
			// Sérialisation de l'image dans la réponse
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "PNG", out);
			out.close();
		}
	}

	/** Méthode qui fournit une image */
	private BufferedImage getImage(String uri, String name) throws IOException {
		return ImageIO.read(new File(getServletContext().getRealPath("/WEB-INF/resources/" + name)));
	}

}