package test.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestBase64 {
	public static void main(String[] args) {
		final String text = "猜猜你是谁？？？？ＬＬｑｑ";

		final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);

		final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		System.out.println(decoded);
	}
}
