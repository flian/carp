import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 2017-01-13
 * Time: 11:11
 */
class ExampleTest extends Specification {
    def "maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7

    }
}
