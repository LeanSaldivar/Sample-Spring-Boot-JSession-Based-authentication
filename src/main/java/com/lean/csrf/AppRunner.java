package com.lean.csrf;

import com.lean.csrf.infras.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("========================================================");
        logger.info("Application fully started. Running startup logic...");
        logger.info("Performing Startup check by calling User Repository...", userRepository.count());
        logger.info("Startup logic complete.");
        logger.info("========================================================");
    }
}
