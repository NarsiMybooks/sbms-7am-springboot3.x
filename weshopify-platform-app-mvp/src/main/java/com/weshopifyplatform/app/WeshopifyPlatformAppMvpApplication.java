package com.weshopifyplatform.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class WeshopifyPlatformAppMvpApplication implements CommandLineRunner{
	
	@Autowired
	private HikariDataSource hds;

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyPlatformAppMvpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/**
		 * The property controls the maximum number of connections 
		 * that HikariCP will keep in the pool,including both 
		 * idle and in-use connections. 
		 */
		System.out.println("maximum pool size:\t"+hds.getMaximumPoolSize());
		
		/**
		 * This property controls the maximum lifetime of a connection in the pool. 
		 * When a connection reaches this timeout, even if recently used, 
		 * it will be retired from the pool. An in-use connection will never be retired, 
		 * only when it is idle will it be removed
		 */
		System.out.println("Conmplete Life Time(In Millis):\t"+hds.getMaxLifetime());
		
		/**
		 * This property controls the maximum amount of time (in milliseconds) that 
		 * a connection is allowed to sit idle in the pool. 
		 * Whether a connection is retired as idle or not is subject to a maximum 
		 * variation of +30seconds, and average variation of +15 seconds. 
		 * A connection will never be retired as idle before this timeout.
		 * A value of 0 means that idle connections are never removed from the pool. 
		 */
		
		System.out.println("idle time out (in Millis):\t"+hds.getIdleTimeout());
		
		/**
		 * Get the maximum number of milliseconds that a client 
		 * will wait for a connection from the pool. 
		 * If thistime is exceeded without a connection becoming available, 
		 * a SQLException will be thrown
		 */
		System.out.println("Connection Time out(in Millis):\t"+hds.getConnectionTimeout());
		
		/**
		 * controls the minimum number of idle connections that HikariCP tries to maintain in the pool,including both idle and in-use connections. 
		 */
		System.out.println(hds.getMinimumIdle());
		
	}

}
