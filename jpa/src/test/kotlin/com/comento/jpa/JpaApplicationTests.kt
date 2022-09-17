package com.comento.jpa

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


internal fun String.toSingleStringWithoutSpace() = this.trimIndent().replace("\n", "").replace(" ", "")

@SpringBootTest
class JpaApplicationTests {

	@Test
	fun contextLoads() {
	}

}
