package app.book;

import app.util.*;
import spark.*;
import java.util.*;
import static app.util.JsonUtil.*;
import static app.util.RequestUtil.*;

public class BookController {

    public static Route fetchAllBooks = (Request request, Response response) -> {
        ViewUtil.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("books", BookDAO.getAllBooks());
            return ViewUtil.render(request, model, Path.Template.BOOKS_ALL);
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(BookDAO.getAllBooks());
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };

    public static Route fetchOneBook = (Request request, Response response) -> {
        ViewUtil.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            Book book = BookDAO.getBookByIsbn(getParamIsbn(request));
            model.put("book", book);
            return ViewUtil.render(request, model, Path.Template.BOOKS_ONE);
        }
        if (clientAcceptsJson(request)) {
            return dataToJson(BookDAO.getBookByIsbn(getParamIsbn(request)));
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
}
