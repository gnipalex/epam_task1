package com.epam.hnyp.shop.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

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

public class GzipResponseFilter implements Filter {

	public static final String CONTENT_TYPE_PART = "text/html";

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		if (supportsGzip(httpReq)) {
			GzipResponseWraper responseWraper = new GzipResponseWraper(httpResp);
			chain.doFilter(request, responseWraper);
			responseWraper.flushBuffer();
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean supportsGzip(HttpServletRequest req) {
		String acceptEncodingHeader = req.getHeader("Accept-encoding");
		if (acceptEncodingHeader == null) {
			return false;
		}
		return acceptEncodingHeader.contains("gzip");
	}

	private class GzipResponseWraper extends HttpServletResponseWrapper {
		private GZIPOutputStream gzipStream;
		private ServletOutputStream servletOutputStream;
		private PrintWriter printWriter;
		private boolean writerCreated;
		private boolean streamCreated;
		private boolean useGzip;

		public GzipResponseWraper(HttpServletResponse response) {
			super(response);
		}
		
		private void initServletOutputStream() throws IOException {
			if (useGzip) {
				setHeader("Content-Encoding", "gzip");
				gzipStream = new GZIPOutputStream(getResponse().getOutputStream());
				servletOutputStream = new ServletOutputStreamWraper(gzipStream);
			} else {
				servletOutputStream = new ServletOutputStreamWraper(getResponse().getOutputStream());
			}
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (streamCreated) {
				throw new IllegalStateException(
						"getOutputStream() method already called");
			}
			if (!writerCreated) {
				initServletOutputStream();
				printWriter = new PrintWriter(new OutputStreamWriter(
						servletOutputStream, getCharacterEncoding()));
				writerCreated = true;
			}
			return printWriter;
		}

		@Override
		public void setContentType(String type) {
			super.setContentType(type);
			if (type.contains("text/")) {
				useGzip = true;
			}
		}
		
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writerCreated) {
				throw new IllegalStateException(
						"getWriter() method already called");
			}
			if (!streamCreated) {
				initServletOutputStream();
				streamCreated = true;
			}
			return servletOutputStream;
		}
		
		@Override
		public void flushBuffer() throws IOException {
			if (printWriter != null) {
				printWriter.flush();
			}
			if (servletOutputStream != null) {
				servletOutputStream.flush();
			}
			if (gzipStream != null) {
				gzipStream.finish();
				gzipStream.flush();
			}
			super.flushBuffer();
		}

		private class ServletOutputStreamWraper extends ServletOutputStream {
			private OutputStream realOutput;

			public ServletOutputStreamWraper(OutputStream realOutput) {
				this.realOutput = realOutput;
			}

			@Override
			public void write(int b) throws IOException {
				realOutput.write(b);
			}
			
			@Override
			public void flush() throws IOException {
				realOutput.flush();
			}
			
			@Override
			public void close() throws IOException {
				realOutput.close();
			}
		}
	}
}
