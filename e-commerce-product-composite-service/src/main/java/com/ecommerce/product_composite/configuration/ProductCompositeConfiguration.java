package com.ecommerce.product_composite.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Configuration
public class ProductCompositeConfiguration {

	@Value("${api.common.version}")
	String apiVersion;
	@Value("${api.common.title}")
	String apiTitle;
	@Value("${api.common.description}")
	String apiDescription;
	@Value("${api.common.termsOfService}")
	String apiTermsOfService;
	@Value("${api.common.license}")
	String apiLicense;
	@Value("${api.common.licenseUrl}")
	String apiLicenseUrl;
	@Value("${api.common.externalDocDesc}")
	String apiExternalDocDesc;
	@Value("${api.common.externalDocUrl}")
	String apiExternalDocUrl;
	@Value("${api.common.contact.name}")
	String apiContactName;
	@Value("${api.common.contact.url}")
	String apiContactUrl;
	@Value("${api.common.contact.email}")
	String apiContactEmail;
	@Value("${app.threadPoolSize:10}")
	Integer threadPoolSize;
	@Value("${app.taskQueueSize:100}")
	Integer taskQueueSize;

	@Bean
	public Scheduler publishEventScheduler() {
//		LOG.info("Creates a messagingScheduler with connectionPoolSize = {}", threadPoolSize);
		return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "publish-pool");
	}

	@Bean
	OpenAPI getOpenApiDocumentation() {
		return new OpenAPI()
			.info(new Info().title(apiTitle)
				.description(apiDescription)
				.version(apiVersion)
				.contact(new Contact()
					.name(apiContactName)
					.url(apiContactUrl)
					.email(apiContactEmail))
				.termsOfService(apiTermsOfService)
				.license(new License()
					.name(apiLicense)
					.url(apiLicenseUrl)))
			.externalDocs(new ExternalDocumentation()
				.description(apiExternalDocDesc)
				.url(apiExternalDocUrl));
	}

	@Bean
	@LoadBalanced
	WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

}
