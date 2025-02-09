package com.ecommerce.product_composite.service;

import static org.springframework.http.HttpMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.common.api.ProductAPI;
import com.ecommerce.common.api.RecommendationAPI;
import com.ecommerce.common.api.ReviewAPI;
import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.common.exception.HttpErrorInfo;
import com.ecommerce.common.exception.InvalidInputException;
import com.ecommerce.common.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Component
@Data
//@Log4j2
//@RequiredArgsConstructor
public class ProductCompositeIntegrationService implements ProductAPI, RecommendationAPI, ReviewAPI {

	private static final String SEMICOLON = ":";
	private static final String REVIEW = "/review/";
	private static final String RECOMMENDATION = "/recommendation/";
	private static final String PRODUCT = "/product/";
	private static final String HTTP = "http://";
	private final RestTemplate restTemplate;
	private final ObjectMapper mapper;

	private final String productServiceUrl;
	private final String recommendationServiceUrl;
	private final String reviewServiceUrl;

	public ProductCompositeIntegrationService(
			final RestTemplate restTemplate,
			final ObjectMapper mapper,
			@Value("${app.product-service.host}") final String productServiceHost,
			@Value("${app.product-service.port}") final int productServicePort,
			@Value("${app.recommendation-service.host}") final String recommendationServiceHost,
			@Value("${app.recommendation-service.port}") final int recommendationServicePort,
			@Value("${app.review-service.host}") final String reviewServiceHost,
			@Value("${app.review-service.port}") final int reviewServicePort) {

		this.restTemplate = restTemplate;
		this.mapper = mapper;

		productServiceUrl = HTTP + productServiceHost + SEMICOLON + productServicePort + PRODUCT;
		recommendationServiceUrl = HTTP + recommendationServiceHost + SEMICOLON + recommendationServicePort + RECOMMENDATION;
		reviewServiceUrl = HTTP + reviewServiceHost + SEMICOLON + reviewServicePort + REVIEW;
	}

	@Override
	public ProductDTO getProduct(final int productId) {

		try {
			final String url = productServiceUrl + productId;
//			log.debug("Will call getProduct API on URL: {}", url);

			final ProductDTO product = Optional.ofNullable(restTemplate.getForObject(url, ProductDTO.class))
				.orElseThrow(() -> new NotFoundException("Product not found"));
//			log.debug("Found a product with id: {}", product.getProductId());

			return product;

		} catch (final HttpClientErrorException ex) {

			switch (HttpStatus.resolve(ex.getStatusCode().value())) {
				case NOT_FOUND:
					throw new NotFoundException(getErrorMessage(ex));

				case UNPROCESSABLE_ENTITY:
					throw new InvalidInputException(getErrorMessage(ex));

				default:
//					log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
//					log.warn("Error body: {}", ex.getResponseBodyAsString());
					throw ex;
			}
		}
	}

	private String getErrorMessage(final HttpClientErrorException ex) {
		try {
			return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
		} catch (final IOException ioex) {
			return ex.getMessage();
		}
	}

	@Override
	public List<RecommendationDTO> getRecommendations(final int productId) {

		try {
			final String url = recommendationServiceUrl + productId;

//			log.debug("Will call getRecommendations API on URL: {}", url);
			final List<RecommendationDTO> recommendations = restTemplate
				.exchange(url, GET, null, new ParameterizedTypeReference<List<RecommendationDTO>>() {
				})
				.getBody();

//			log.debug("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
			return recommendations;

		} catch (final Exception ex) {
//			log.warn("Got an exception while requesting recommendations, return zero recommendations: {}", ex.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public List<ReviewDTO> getReviewsByProductId(final int productId) {

		try {
			final String url = reviewServiceUrl + productId;

//			log.debug("Will call getReviews API on URL: {}", url);
			final List<ReviewDTO> reviews = restTemplate
				.exchange(url, GET, null, new ParameterizedTypeReference<List<ReviewDTO>>() {
				})
				.getBody();

//			log.debug("Found {} reviews for a product with id: {}", reviews.size(), productId);
			return reviews;

		} catch (final Exception ex) {
//			log.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public void createProduct(final ProductDTO body) {

		try {
			final String url = productServiceUrl;
			System.out.println("Will post a new product to URL: " + url);

			final ProductDTO product = restTemplate.postForObject(url, body, ProductDTO.class);
//			log.debug("Created a product with id: {}", product.getProductId());

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	@Override
	public void deleteProduct(final int productId) {
		try {
			final String url = productServiceUrl + "/" + productId;
//			log.debug("Will call the deleteProduct API on URL: {}", url);

			restTemplate.delete(url);

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	@Override
	public RecommendationDTO createRecommendation(final RecommendationDTO body) {

		try {
			final String url = recommendationServiceUrl;
//			log.debug("Will post a new recommendation to URL: {}", url);

			final RecommendationDTO recommendation = restTemplate.postForObject(url, body, RecommendationDTO.class);
//			log.debug("Created a recommendation with id: {}", recommendation.getProductId());

			return recommendation;

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	@Override
	public void deleteRecommendations(final int productId) {
		try {
			final String url = recommendationServiceUrl + "?productId=" + productId;
//			log.debug("Will call the deleteRecommendations API on URL: {}", url);

			restTemplate.delete(url);

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	@Override
	public ReviewDTO createReview(final ReviewDTO body) {

		try {
			final String url = reviewServiceUrl;
//			log.debug("Will post a new review to URL: {}", url);

			final ReviewDTO review = restTemplate.postForObject(url, body, ReviewDTO.class);
//			log.debug("Created a review with id: {}", review.getProductId());

			return review;

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	@Override
	public void deleteReviewsByProductId(final int productId) {
		try {
			final String url = reviewServiceUrl + "?productId=" + productId;
//			log.debug("Will call the deleteReviews API on URL: {}", url);

			restTemplate.delete(url);

		} catch (final HttpClientErrorException ex) {
			throw handleHttpClientException(ex);
		}
	}

	private RuntimeException handleHttpClientException(final HttpClientErrorException ex) {
		return switch (HttpStatus.resolve(ex.getStatusCode().value())) {
			case NOT_FOUND -> new NotFoundException(getErrorMessage(ex));
			case UNPROCESSABLE_ENTITY -> new InvalidInputException(getErrorMessage(ex));
			default -> {
//				log.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
//				log.warn("Error body: {}", ex.getResponseBodyAsString());
				yield ex;
			}
		};
	}

}
