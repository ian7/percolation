import spock.lang.Specification

class PercolationStatsTest extends Specification {
    def "OneTrial"() {
        given:
        def ps1 = new PercolationStats(3, 1);
        expect:
        ps1.mean() > 0;
    }

    def "Mean"() {
    }

    def "Stddev"() {
    }

    def "ConfidenceLo"() {
    }

    def "ConfidenceHi"() {
    }
}
