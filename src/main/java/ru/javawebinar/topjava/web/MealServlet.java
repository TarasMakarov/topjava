package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.ListMealsInMemoryStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(ru.javawebinar.topjava.web.MealServlet.class);
    private static final Integer CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        List<MealTo> mealTo = MealsUtil.filteredByStreams(ListMealsInMemoryStorage.getMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY);
        request.setAttribute("mealTo", mealTo);
        //        response.sendRedirect("meals.jsp");
        request.getRequestDispatcher("/WEB-INF/jsp/meals.jsp").forward(request, response);
    }
}