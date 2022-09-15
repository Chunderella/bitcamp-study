package com.bitcamp.board;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import com.bitcamp.handler.WelcomeHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



//1) 기본 웹 서버 만들기
//2) 한글 콘텐트 응답하기
//3) HTML 콘텐트를 출력하기
//4) 메인 화면을 출력하는 요청처리 객체를 분리하기


public class MiniWebServer {

  public static void main(String[] args) throws Exception {

    class MyHttpHandler implements HttpHandler {
      @Override
      public void handle(HttpExchange exchange) throws IOException {
        System.out.println("클라이언트가 요청함!");

        URI requestUri = exchange.getRequestURI();
        System.out.println(requestUri.getScheme());
        System.out.println(requestUri.getHost());
        System.out.println(requestUri.getPort());
        System.out.println(requestUri.getPath());

        WelcomeHandler welcomeHandler = new WelcomeHandler();

        byte[] bytes = null;

        try (StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter)) {

          welcomeHandler.service(printWriter);
          bytes= stringWriter.toString().getBytes("UTF-8");
        }

        //보내는 콘텐트의 MIME 타입이 무엇인지 응답 헤더에 추가한다.
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type","text/html; charset=UTF-8"); //html로 랜더링해서 출력

        exchange.sendResponseHeaders(200, bytes.length); 

        OutputStream out = exchange.getResponseBody();
        out.write(bytes);  //바이트로 뽑는다. 따로지정안하면 UTF
        out.close();
      }
    }

    HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
    server.createContext("/",new MyHttpHandler());
    server.setExecutor(null); //HttpServer에 기본으로 설정되어 있는 들어 있는 Executor 사용
    server.start(); //HttpServer를 시작시킨 후 즉시 리턴한다.
    System.out.println("서버 시작!");


  }

}
