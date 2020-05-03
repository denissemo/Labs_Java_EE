import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import DAO.WeatherDao;
import models.Weather;


import DB.DBConnect;

@WebServlet(name = "ForecastServlet")
public class ForecastServlet extends HttpServlet {
    protected DBConnect connect;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            this.connect = new DBConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            // Filters
            String where = "";
            String order = "";
            String cityId = request.getParameter("city");
            String stationId = request.getParameter("station");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String column = request.getParameter("column");
            String direction = request.getParameter("direction");

            if (cityId != null && !cityId.equals("")) {
                session.setAttribute("cityId", cityId);
                where += String.format(" and c.id = %s", cityId);
            } else if (stationId != null && !stationId.equals("")) {
                session.setAttribute("stationId", stationId);
                System.out.println(stationId);
                where += String.format(" and s2.id = %s", stationId);
            } else if (fromDate != null && !toDate.equals("")) {
                where += String.format(" and data >= %s 00:00:00.000 +00:00", fromDate);
            } else if (toDate != null && !toDate.equals("")) {
                where += String.format(" and data < %s 23:59:59.000 +00:00", toDate);
            } else if (column != null && !column.equals("") && direction != null && !direction.equals("")) {
                order += String.format(" order by %s %s", column, direction);
            }

            DBConnect connect = new DBConnect();
            WeatherDao WeatherDao = new WeatherDao(connect);

            ArrayList<Weather> weathers = WeatherDao.get(where, order);
            System.out.println(weathers.get(0).getDate());
            ArrayList<String> keys = new ArrayList<>(
                    Arrays.asList(
                            "date", "city", "stationName", "minTemp", "maxTemp", "wind",
                            "description", "cloud"
                    )
            );

            // save visitor
            String ip = request.getRemoteAddr();
            Visitors visitors = new Visitors(connect);
            visitors.save(ip);
            int count = visitors.count();

            // Set attributes
            request.setAttribute("weathers", weathers);
            request.setAttribute("keys", keys);
            request.setAttribute("cities", WeatherDao.getSelectInfo("city"));
            request.setAttribute("stations", WeatherDao.getSelectInfo("station"));
            request.setAttribute("count", count);

            // Get servlet info
            ServletConfig config = getServletConfig();
            StringBuilder servletConfig = new StringBuilder(config.getServletName() + "; ");
            Enumeration<String> c = config.getInitParameterNames();

            servletConfig.append("Init parameters:");
            while (c.hasMoreElements()) {
                String str = c.nextElement();
                servletConfig.append(String.format(" Name: %s; Value: %s; ", str, config.getInitParameter(str)));
            }

            request.setAttribute("servletConfig", servletConfig.toString());

            request.getRequestDispatcher("table.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}