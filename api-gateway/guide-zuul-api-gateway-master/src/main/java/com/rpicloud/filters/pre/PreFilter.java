package com.rpicloud.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kaspernissen on 04/04/2016.
 */
public class PreFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		 
		String port = request.getParameter("port");
		log.info(port);	
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		log.info(request.getLocalAddr());
		log.info(request.getAuthType());
		log.info(request.getProtocol());

		log.info("REQUEST :: < " + request.getScheme() + " " + request.getLocalAddr() + ":" + request.getLocalPort());
		log.info("REQUEST :: < " + request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol()); 
		log.info("RESPONSE:: > HTTP:" + response.getStatus());
 
        
//		log.info(getClientIp(request));
//		log.info(getMACAddress(getClientIp(request)));
		return null;
	}

	private static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}
	
    public String getMACAddress(String ip){ 
        String str = ""; 
        String macAddress = ""; 
        try { 
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
            LineNumberReader input = new LineNumberReader(ir); 
            for (int i = 1; i <100; i++) { 
                str = input.readLine(); 
                if (str != null) { 
                    if (str.indexOf("MAC Address") > 1) { 
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
                        break; 
                    } 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(System.out); 
        } 
        return macAddress; 
    }
}
