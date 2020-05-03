import DAO.CityDao;
import DAO.WeatherDao;
import DB.DBConnect;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import logger.Logger;
import models.CityStation;

@WebServlet(name = "CityServlet")
public class CityServlet extends HttpServlet {
    Logger logger = null;
    protected DBConnect connect;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            this.connect = new DBConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.logger = new Logger();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnect connect = new DBConnect();
            CityDao City = new CityDao(connect);

            String method = request.getParameter("_method");

            logger.info(String.format("%s /city", method.toUpperCase()));

            switch (method) {
                case "post": {
                    String name = request.getParameter("cityName");

                    logger.info(String.format("Create city: %s", name));

                    if (name != null && !name.equals("")) {
                        City.insert(name);
                    }
                    break;
                }
                case "delete": {
                    String id = request.getParameter("cityId");

                    logger.info(String.format("Delete city with id: '%s'", id));

                    if (id != null && !id.equals("")) {
                        City.delete(id);
                    }
                    break;
                }
                case "put": {
                    String id = request.getParameter("cityId");
                    String name = request.getParameter("cityName");

                    logger.info(String.format("Update city '%s' with name '%s'", id, name));

                    if (id != null && !id.equals("") && name != null) {
                        City.update(id, name);
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnect connect = new DBConnect();

            ArrayList<CityStation> result = new WeatherDao(connect).getSelectInfo("city");

            request.setAttribute("cities", result);
            request.getRequestDispatcher("city.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
