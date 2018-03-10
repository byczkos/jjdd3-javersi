package servlets;

import cdi.ChargingPointToDtoConverterBean;
import dao.ChargingPointDao;
import dto.ChargingPointDto;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/search-by-town")
public class SearchByTownServlet extends HttpServlet {

    @Inject
    private ChargingPointDao chargingPointDao;

    @Inject
    private ChargingPointToDtoConverterBean chargingPointToDtoConverterBean;

    public static final Logger LOG = LoggerFactory.getLogger(SearchByTownServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("title", "Search by town");

        String town = req.getParameter("town");
        if (town == null || town.isEmpty()) {
            dataModel.put("body_template", "search-by-town");
        } else {
            List<ChargingPointDto> chargingPointsDtoList = chargingPointToDtoConverterBean.convertList(chargingPointDao.findByTown(town));
            dataModel.put("body_template", "results");
            dataModel.put("chargingPoints", chargingPointsDtoList);
        }

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        Template template = TemplateProvider.createTemplate(getServletContext(), "layout.ftlh");

        try {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            LOG.error("Template problem occurred.");
        }
    }
}
