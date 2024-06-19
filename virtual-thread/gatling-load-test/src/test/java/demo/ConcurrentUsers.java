package demo;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

// can be omitted if you don't use jdbcFeeder

// used for specifying durations with a unit, eg Duration.ofMinutes(5)


public class ConcurrentUsers extends Simulation {
    // http
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8100");
    // headers

    // scenario

    private ScenarioBuilder scn = scenario("ConcurrentUsersSimulation")
            .exec(http("Concurrent Users")
                    .get("/demo/greeting")
            );

    {
        setUp(scn.injectOpen(
                atOnceUsers(7)
        ))
                .protocols(httpProtocol);
    }

}
