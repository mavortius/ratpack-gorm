package demo

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class RatpackGormSpec extends Specification {

    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest test = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient client = test.httpClient

    void "Get data from database"() {
        when:
        get()

        then:
        response.statusCode == 200

        when:
        def json = new JsonSlurper().parseText(response.body.text)

        then:
        json.size() == 3
        json*.firstName == ['Marcelo', 'Magaly', 'Edna']
    }
}
