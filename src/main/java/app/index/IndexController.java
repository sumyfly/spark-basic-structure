package app.index;

import app.book.*;
import app.user.*;
import app.util.*;
import spark.*;
import java.util.*;

public class IndexController {
    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("users", UserDAO.getAllUserNames());
        model.put("book", BookDAO.getRandomBook());
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };
}
