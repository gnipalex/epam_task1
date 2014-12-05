package com.epam.hnyp.shop.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipFilter2 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		if (supportsGzip(httpReq)) {
			chain.doFilter(request, new ResponseWraper(httpResp));
			System.out.println("used wraper");
		} else {
			chain.doFilter(request, httpResp);
		}
	}

	private boolean supportsGzip(HttpServletRequest req) {
		String acceptEncodingHeader = req.getHeader("Accept-encoding");
		if (acceptEncodingHeader == null) {
			return false;
		}
		return acceptEncodingHeader.contains("gzip");
	}
	
	private class ResponseWraper extends HttpServletResponseWrapper {

		public ResponseWraper(HttpServletResponse response) {
			super(response);
		}
		
	}
	
	private class MyServletStreamWraper extends ServletOutputStream {
		private ByteArrayOutputStream baos;
		private OutputStream realOutput;
		private boolean closed;

		public MyServletStreamWraper(OutputStream realOutput) {
			this.realOutput = realOutput;
			this.baos = new ByteArrayOutputStream();
		}
		
		@Override
		public void write(int b) throws IOException {
			baos.write(b);
		}
		
		@Override
		public void close() throws IOException {
			if (closed) {
				throw new IOException("already closed");
			}
			
		}
		
		@Override
		public void flush() throws IOException {
			baos.flush();
			
		}
		
	}

}
