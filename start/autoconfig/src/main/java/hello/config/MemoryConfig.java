package hello.config;

import memory.MemoriFinder;
import memory.MemoryCondition;
import memory.MemoryController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional(MemoryCondition.class)
@ConditionalOnProperty(name = "memory", havingValue = "on")
public class MemoryConfig {

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoriFinder());
    }

    @Bean
    public MemoriFinder memoriFinder() {
        return new MemoriFinder();
    }
}
