package io.blockfrost.sdk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * EndpointUsageMetric
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EndpointUsageMetric {

    /**
     * Starting time of the call count interval (ends midnight UTC) in UNIX time.
     */
    Long time;
    /**
     * Sum of all calls for a particular day and endpoint.
     */
    Long calls;
    /**
     * Endpoint parent name.
     */
    String endpoint;

}
