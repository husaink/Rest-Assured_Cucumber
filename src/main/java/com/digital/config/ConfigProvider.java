package com.digital.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigProvider {

	private static Config config;

	public static Config config() {
		if (config == null) {
			config = ConfigFactory.load();
		}

		return config;
	}

}
