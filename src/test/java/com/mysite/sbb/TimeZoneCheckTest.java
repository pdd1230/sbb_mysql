package com.mysite.sbb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeZoneCheckTest {

	@Autowired
	private DataSource dataSource;

	@Test
	void checkJdbcTimeZone() throws Exception {
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			// JDBC 세션 타임존 확인
			ResultSet rs1 = stmt.executeQuery("SELECT @@session.time_zone");
			if (rs1.next()) {
				System.out.println("JDBC Session Time Zone = " + rs1.getString(1));
			}

			// JDBC 세션 기준 현재 시각 확인
			ResultSet rs2 = stmt.executeQuery("SELECT NOW()");
			if (rs2.next()) {
				System.out.println("JDBC Session NOW() = " + rs2.getString(1));
			}
		}
	}
}
