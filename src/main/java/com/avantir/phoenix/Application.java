package com.avantir.phoenix;

import com.avantir.phoenix.messaging.EndpointStarter;
import javafx.util.converter.ByteStringConverter;
import javassist.bytecode.ByteArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lekanomotayo on 01/01/2018.
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
