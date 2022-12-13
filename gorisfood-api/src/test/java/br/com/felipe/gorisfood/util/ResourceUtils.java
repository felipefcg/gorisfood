package br.com.felipe.gorisfood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

public class ResourceUtils {

		public static String getContentFromResource(String resourceName) {
			
			try {
				InputStream is = ResourceUtils.class.getClassLoader().getResourceAsStream(resourceName);
				return StreamUtils.copyToString(is, StandardCharsets.UTF_8);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
}
