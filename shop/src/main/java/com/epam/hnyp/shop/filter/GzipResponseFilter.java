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

public class GzipResponseFilter implements Filter {

	public static final String CONTENT_TYPE_PART = "text/html";

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		HttpServletResponse httpResp = (HttpServletResponse) response;
//		if (supportsGzip(httpReq)) {
//			GzipResponseWraper responseWraper = new GzipResponseWraper(httpResp);
//			chain.doFilter(request, responseWraper);
//			String usedContentType = responseWraper.getContentType();
//			boolean encode = false;
//			if (usedContentType != null
//					&& usedContentType.contains(CONTENT_TYPE_PART)) {
//				encode = true;
//				httpResp.setHeader("Content-encoding", "gzip");
//			}
//			writeToRealStream(responseWraper.getWrapedOutput(),
//					httpResp.getOutputStream(), encode);
//			responseWraper.closeAllStreams();
//		} else {
//			chain.doFilter(request, response);
//		}
	}

	private void writeToRealStream(ByteArrayOutputStream sourceStream,
			OutputStream destinationStream, boolean encode) throws IOException {
		if (encode) {
			GZIPOutputStream gzipOutStream = new GZIPOutputStream(
					destinationStream);
			sourceStream.writeTo(gzipOutStream);
			gzipOutStream.finish();
			gzipOutStream.flush();
		} else {
			sourceStream.writeTo(destinationStream);
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

		private ByteArrayOutputStream byteArrayOutputStream;
		//private GZIPOutputStream gzipStream;
		private ServletOutputStream servletOutputStream;
		private PrintWriter printWriter;
		private boolean writerCreated;
		private boolean streamCreated;

		public ResponseWraper(HttpServletResponse response) {
			super(response);
		}
		
		private void init() throws IOException {
			byteArrayOutputStream = new ByteArrayOutputStream();
			//gzipStream = new GZIPOutputStream(byteArrayOutputStream);
			servletOutputStream = new ServletOutputStreamWraper(byteArrayOutputStream);
			printWriter = new PrintWriter(new OutputStreamWriter(
						servletOutputStream, getCharacterEncoding()));
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (streamCreated) {
				throw new IllegalStateException(
						"getOutputStream() method already called");
			}
			if (!writerCreated) {
				init();
				writerCreated = true;
			}
			return printWriter;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writerCreated) {
				throw new IllegalStateException(
						"getWriter() method already called");
			}
			if (!streamCreated) {
				init();
				streamCreated = true;
			}
			return servletOutputStream;
		}
		
		@Override
		public void flushBuffer() throws IOException {
			servletOutputStream.flush();
		}

		public ByteArrayOutputStream getWrapedOutput() {
			return byteArrayOutputStream;
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
