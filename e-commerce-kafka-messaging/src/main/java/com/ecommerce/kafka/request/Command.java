package com.ecommerce.kafka.request;

/**
 * @author lex_looter
 *
 *         6 apr 2025
 *
 */
// TODO use annotations from RequestType because Jackson can't deserialize non-concrete classes right away
public interface Command {

	void execute();

}
