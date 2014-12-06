package com.epam.hnyp.shop.filter;

import java.io.ByteArrayOutputStream;
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

import org.apache.log4j.Logger;

public class New_GzipResponseFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(New_GzipResponseFilter.class);

	public static final String ACCEPT_ENCODING_HEADER = "Accept-encoding";
	public static final String CONTENT_TYPE_PART = "text/";

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		System.out.println(httpReq.getRequestURI());
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
		// private GZIPOutputStream gzipStream;
		private ByteArrayOutputStream byteArrayOutputStream;
		private ServletOutputStream servletOutputStream;
		private PrintWriter printWriter;
		private boolean writerCreated;
		private boolean streamCreated;
		private boolean useGzip;

		public GzipResponseWraper(HttpServletResponse response) {
			super(response);
			this.byteArrayOutputStream = new ByteArrayOutputStream();
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (streamCreated) {
				throw new IllegalStateException(
						"getOutputStream() method already called");
			}
			LOG.info("writer created");
			if (!writerCreated) {
				servletOutputStream = new GzipWiseServletOutputStreamWraper(byteArrayOutputStream);
				printWriter = new PrintWriter(new OutputStreamWriter(
						servletOutputStream, getCharacterEncoding()));
				writerCreated = true;
			}
			return printWriter;
		}

//		@Override
//		public void setContentType(String type) {
//			super.setContentType(type);
//			LOG.info(type);
//			if (type.contains(CONTENT_TYPE_PART)) {
//				useGzip = true;
//			}
//		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writerCreated) {
				throw new IllegalStateException(
						"getWriter() method already called");
			}
			LOG.info("outputstream created");
			if (!streamCreated) {
				servletOutputStream = new GzipWiseServletOutputStreamWraper(byteArrayOutputStream);
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
				servletOutputStream.close();
			}
			this.getResponse().setContentLength(byteArrayOutputStream.size());
			if (useGzip) {
				this.setHeader("Content-Encoding", "gzip");
			}
			byteArrayOutputStream.writeTo(this.getResponse().getOutputStream());
			byteArrayOutputStream.reset();
			super.flushBuffer();
		}

		private class GzipWiseServletOutputStreamWraper extends
				ServletOutputStream {
			private GZIPOutputStream gzipOutputStream;
			private OutputStream outputStream;
			private OutputStream realOutputStream;
			private boolean writeAlreadyCalled = false;

			public GzipWiseServletOutputStreamWraper(OutputStream realOutput) {
				this.realOutputStream = realOutput;
			}

			@Override
			public void write(int b) throws IOException {
				if (!writeAlreadyCalled) {
					createUnderStream();
					writeAlreadyCalled = true;
				}
				this.outputStream.write(b);
			}

			private void createUnderStream() throws IOException {
				if (GzipResponseWraper.this.getContentType().contains(CONTENT_TYPE_PART)) {
//					GzipResponseWraper.this.setHeader("Content-Encoding",
//							"gzip");
					this.gzipOutputStream = new GZIPOutputStream(
							realOutputStream, true);
					this.outputStream = this.gzipOutputStream;
					
					GzipResponseWraper.this.useGzip = true;
				} else {
					this.outputStream = realOutputStream;
				}
			}

			@Override
			public void flush() throws IOException {
				if (gzipOutputStream != null) {
					gzipOutputStream.flush();
				}
			}

			@Override
			public void close() throws IOException {
				if (gzipOutputStream != null) {
					gzipOutputStream.close();
				}
			}
		}
	}
}
