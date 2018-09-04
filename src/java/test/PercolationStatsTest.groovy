import spock.lang.Specification

class PercolationStatsTest extends Specification {
    def "OneTrial"() {
        given:
        def ps1 = new PercolationStats(3, 1);
        expect:
        ps1.mean() > 0;
    }

    def "FirstExample"() {
        given:
        def ps1 = new PercolationStats(200, 100);
        expect:
        Math.abs(ps1.mean() - 0.59) < 0.01;
    }

    def "2ndExample"() {
        given:
        def ps1 = new PercolationStats(2, 10000);
        expect:
        Math.abs(ps1.mean() - 0.66) < 0.01;
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
