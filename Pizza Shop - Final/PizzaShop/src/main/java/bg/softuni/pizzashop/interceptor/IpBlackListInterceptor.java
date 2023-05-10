package bg.softuni.pizzashop.interceptor;


import bg.softuni.pizzashop.service.IpBlackListService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {

    private final IpBlackListService ipBlackListService;

    private final ThymeleafViewResolver thymeleafViewResolver;

    public IpBlackListInterceptor(IpBlackListService ipBlackListService, ThymeleafViewResolver thymeleafViewResolver) {
        this.ipBlackListService = ipBlackListService;
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //this method is executed before each request checks if the ip is banned
        String ip = getIpAddressFromRequest(request);
        if(ipBlackListService.isBlackListed(ip)) {
            View blockedView = thymeleafViewResolver.resolveViewName("blocked", Locale.getDefault());
            if(blockedView != null) {
            blockedView.render(Map.of(), request, response);
            }
            return false;
        }
        return true;
    }

    private String getIpAddressFromRequest(HttpServletRequest request) {

        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader != null && !xffHeader.equals("unknown")) {
            int commaIdx = xffHeader.indexOf(",");
            if (commaIdx > 0) {
                ipAddress = xffHeader.substring(0, commaIdx - 1);
            }
        }

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
