package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServlet5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServlet5  extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); // object를 통해 어떤 controller가 들어와도 된다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServlet5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3()); // new-form으로 지정, formController로 이동
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3()); // save로 지정, saveController로 이동
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3()); // members로 지정, ListController로 이동
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request); // 1. 핸들러 조회 (핸들러 매핑 정보)

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 2. 핸들러를 처리할 수 있는 핸들러 조회 (핸들러 어댑터 목록)
        
        ModelView mv = adapter.handle(request, response, handler); // 3, 4, 5


        String viewName = mv.getViewName(); // 6. viewResolver 호출
        MyView view = viewResolver(viewName); // 7. Myview 반환

        view.render(mv.getModel(), request, response); // 8. render(model) 호출
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler =" + handler);
    }


    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}