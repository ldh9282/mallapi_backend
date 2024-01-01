package org.zerock.mallapi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MallapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallapiApplication.class, args);
	}

	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${server.port}")
	private String serverPort;
	
	/***
	 * @기능 서버구동시작후 실행되는 메서드
	 */
	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("=======================================================================================================");
			System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss")) + " 서버구동이 완료되었습니다");
			System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss")) + " " + getServerIP() +  ":" + serverPort + contextPath);
			System.out.println("=======================================================================================================");
			
		};
	}
	
	/***
	 * @기능 localhost 대신 서버 ip 직접 확인용도
	 */
	private static String getServerIP() {
		String serverIP = null;
		
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			serverIP = localhost.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		return serverIP; 
	}
}
