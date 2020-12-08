package com.ipman.jta.atomikosframe;

import com.ipman.jta.atomikosframe.config.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AtomikosFrameApplicationTests {

	@Autowired
	Demo demo;

	@Test
	void contextLoads() {

		demo.run();
	}

}
