package org.telegram.telegraph;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackages={"org.telegram.telegraph"})
@Lazy(false)
public class Config {
}
