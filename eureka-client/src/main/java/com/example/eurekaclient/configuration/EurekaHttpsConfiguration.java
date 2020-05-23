package com.example.eurekaclient.configuration;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
@Profile({"https"})
public class EurekaHttpsConfiguration {

    @Value("${eureka.client.ssl.key-store}")
    private String ketStoreFileName;

    @Value("${eureka.client.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs()
            throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("eureka-https-client");
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(
                        this.getClass().getClassLoader().getResource(ketStoreFileName),
                        keyStorePassword.toCharArray()
                )
                .build();
        builder.withCustomSSL(sslContext);

        builder.withMaxTotalConnections(10);
        builder.withMaxConnectionsPerHost(10);

        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}
