package com.example.jpastudent;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaStudentApplication {
	// 为了使用shiro，这里还需要加一堆东西，详见 https://www.youtube.com/watch?v=VjUp4wENKdE&list=PLSY2q1ZYmTAS2733EOtjcnPC46IM3Q0nP&index=5
	@Bean
	public Realm realm() {
		// 默认会使用 shiro-users.properties
		PropertiesRealm myRealm = new PropertiesRealm();
		return myRealm;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaStudentApplication.class, args);
	}

}
